package com.client.register.services.address

import com.client.register.dtos.AddressDTO
import com.client.register.mappers.toDTO
import com.client.register.repositories.AddressRepository
import com.client.register.validators.Validator
import mu.two.KotlinLogging
import org.springframework.beans.BeanUtils
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import java.util.Objects

@Service
class AddressUpdaterService (
    private val repository: AddressRepository,
    private val validator: Validator<AddressDTO>
): UpdateAddressService {

    private val log = KotlinLogging.logger {}

    override fun updateById(address: AddressDTO, addressId: Int): AddressDTO {
        log.info { "=M AddressUpdaterService.updateById(), stage=init, addressId${addressId}" }
        validator.validate(address)

        val savedAddress = repository.findById(addressId)
                                                         .orElseThrow { NotFoundException() }

        BeanUtils.copyProperties(address, savedAddress,"main", "addressType")

        val done = repository.save(savedAddress).toDTO()

        log.info { "=M AddressUpdaterService.updateById(), stage=finish, addressId${addressId}" }
        return done
    }

    override fun favoriteAddress(addressId: Int, clientId: Int){
        log.info { "=M AddressUpdaterService.favoriteAddress(), stage=init, addressId${addressId}" }
        val addresses = repository.findAllByClientId(clientId)
        addresses.find { it.main }?.unfavorite()

        val newFavorite = addresses.find { it.id == addressId }
        if (Objects.isNull(newFavorite)) throw NotFoundException()

        newFavorite!!.favorite()

        log.info { "=M AddressUpdaterService.favoriteAddress(), stage=finish, addressId${addressId}" }
        repository.saveAll(addresses)
    }
}
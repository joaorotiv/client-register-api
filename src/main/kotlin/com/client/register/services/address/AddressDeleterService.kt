package com.client.register.services.address

import com.client.register.exceptions.DeleteFavoriteAddressException
import com.client.register.repositories.AddressRepository
import mu.two.KotlinLogging
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

@Service
class AddressDeleterService (
    private val repository: AddressRepository
): DeleteAddressService {

    private val log = KotlinLogging.logger {}

    override fun deleteAddressById(addressId: Int) {
        log.info { "M=AddressDeleterService.deleteAddressById, stage=init, addressId${addressId}"}

        val address = repository.findById(addressId)
                                .orElseThrow { NotFoundException() }

        if (address.isFavorite()) throw DeleteFavoriteAddressException("")

        repository.deleteById(addressId)

        log.info { "M=AddressDeleterService.deleteAddressById, stage=finish, addressId${addressId}"}
    }
}
package com.client.register.services.address

import com.client.register.dtos.AddressDTO
import com.client.register.exceptions.ZipcodeNotFoundException
import com.client.register.mappers.toDTO
import com.client.register.repositories.AddressRepository
import com.client.register.repositories.ClientRepository
import mu.two.KotlinLogging
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

@Service
class AddressGetterService(
    private val repository: AddressRepository,
    private val clientRepository: ClientRepository
): GetAddressService {

    private val log = KotlinLogging.logger {}

    override fun findAddressById(addressId: Int) : AddressDTO {
        log.info { "M=AddressGetterService.findAddressById, stage=init, addressId${addressId}"}
        val done = repository.findById(addressId)
            .orElseThrow { NotFoundException() }

        log.info { "M=AddressGetterService.findAddressById, stage=finish, addressId${addressId}"}
        return done.toDTO()
    }

    override fun findAllAddressesFromClient(clientId: Int) : List<AddressDTO> {
        log.info { "M=AddressGetterService.findAllAddressesFromClient, stage=init, clientId${clientId}"}
        val client = clientRepository.findById(clientId)
            .orElseThrow { NotFoundException() }

        val done = client.addresses!!.map { it.toDTO() }

        log.info { "M=AddressGetterService.findAllAddressesFromClient, stage=finish, clientId${clientId}"}
        return done
    }

    override fun findAddressByZipcode(zipcode: String, clientId:Int) : List<AddressDTO>{
        log.info { "M=AddressGetterService.findAddressByZipcode, stage=init, zipcode${zipcode}"}

        val done = repository.findAddressByZipcode(zipcode, clientId ).map { it.toDTO() }
            .ifEmpty { throw ZipcodeNotFoundException("") }

        log.info { "M=AddressGetterService.findAddressByZipcode, stage=finish, zipcode${zipcode}"}
        return done
    }
}
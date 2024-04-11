package com.client.register.services.address

import com.client.register.helper.ClientApiHelper
import com.client.register.dtos.AddressDTO
import com.client.register.exceptions.ExceedMaxNumberOfAddresses
import com.client.register.mappers.toDTO
import com.client.register.mappers.toEntity
import com.client.register.repositories.AddressRepository
import com.client.register.validators.Validator
import mu.two.KotlinLogging
import org.springframework.stereotype.Service

@Service
class AddressCreatorService(
    private val repository: AddressRepository,
    private val validator: Validator<AddressDTO>,
    private val helper: ClientApiHelper
): CreateAddressService {

    private val log = KotlinLogging.logger {}

    override fun execute(address: AddressDTO, clientId: Int) : AddressDTO {
        log.info { "M=AddressCreatorService.execute, stage=init" }
        validator.validate(address)
        helper.setAddressType(address, clientId)

        val client = helper.findClientOrThrow(clientId)
        if (client.maxNumberOfAddresses()) throw ExceedMaxNumberOfAddresses("")

        client.ableToPurchase()
        helper.favoriteNewAddress(address, client)

        val saved = repository.save(address.toEntity(client))

        log.info { "M=AddressCreatorService.execute, stage=finish" }
        return saved.toDTO()
    }
}
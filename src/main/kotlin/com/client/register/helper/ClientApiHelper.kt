package com.client.register.helper

import com.client.register.dtos.AddressDTO
import com.client.register.entities.ClientEntity
import com.client.register.enums.AddressType
import com.client.register.enums.ClientType
import com.client.register.repositories.ClientRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

@Service
class ClientApiHelper(
    private val clientRepository: ClientRepository
) {

    fun findClientOrThrow(clientId: Int): ClientEntity {
        return clientRepository.findById(clientId)
            .orElseThrow { NotFoundException() }
    }

     fun favoriteNewAddress(address: AddressDTO, client : ClientEntity){
        if (address.isFavorite()) client.addresses?.find { it.main }?.unfavorite()
    }

    fun setAddressType(address: AddressDTO, clientId: Int){
        val client = findClientOrThrow(clientId)

        if(client.clientType == ClientType.JURIDICO ){
            address.addressType = AddressType.COMERCIAL
        } else {
            address.addressType = AddressType.RESIDENCIAL
        }
    }
}
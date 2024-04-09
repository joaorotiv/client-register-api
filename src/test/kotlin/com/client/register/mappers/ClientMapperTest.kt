package com.client.register.mappers

import com.client.register.dtos.AddressDTO
import com.client.register.dtos.ClientDTO
import com.client.register.entities.AddressEntity
import com.client.register.entities.ClientEntity
import com.client.register.enums.AddressType
import com.client.register.enums.ClientDocumentType
import com.client.register.enums.ClientType
import java.time.LocalDateTime

fun dummyClientDTO() = ClientDTO(
    clientId = 1,
    name = "name",
    email = "email@exemplo.com",
    clientType = ClientType.FISICO,
    documentType = ClientDocumentType.CPF,
    documentNumber = "199.199.199-19",
    pendingRegistration = true,
    addresses = mutableListOf(
        AddressDTO(
            addressType = AddressType.RESIDENCIAL,
            publicArea = "Rua 1-A",
            residenceNumber = "1",
            complement = "Qd 13, Lt 4",
            neighborhood = "Bairro das Garças",
            city = "São Paulo",
            state = "SP",
            country = "Brazil",
            zipcode = "11111-11",
            pointOfReference = "próximo a um hospital",
            main = false
        ),
        AddressDTO(
            addressType = AddressType.RESIDENCIAL,
            publicArea = "Rua 2-A",
            residenceNumber = "2",
            complement = "Qd 14, Lt 5",
            neighborhood = "Vale das Garcas",
            city = "São Paulo",
            state = "SP",
            country = "Brazil",
            zipcode = "22222-22",
            pointOfReference = "próximo a um hospital",
            main = true
        )
    ),
    purchasePermission = false,
    registerDate = LocalDateTime.now(),
    lastRegisterUpdate = LocalDateTime.now()
)

fun dummyClientEntity() = ClientEntity(
    id = 1,
    name = "name",
    email = "email@exemplo.com",
    clientType = ClientType.FISICO,
    documentType = ClientDocumentType.CPF,
    documentNumber = "199.199.199-19",
    purchasePermission = false,
    pendingRegistration = true,
    addresses = listOf(
        AddressEntity(
            addressType = AddressType.RESIDENCIAL,
            publicArea = "Rua 1-A",
            residenceNumber = "1",
            complement = "Qd 13, Lt 4",
            neighborhood = "Bairro das Garças",
            city = "São Paulo",
            state = "SP",
            country = "Brazil",
            zipcode = "11111-11",
            pointOfReference = "próximo a um hospital",
            main = true
    ),
        AddressEntity(
            addressType = AddressType.RESIDENCIAL,
            publicArea = "Rua 2-A",
            residenceNumber = "2",
            complement = "Qd 14, Lt 5",
            neighborhood = "Vale das Garcas",
            city = "São Paulo",
            state = "SP",
            country = "Brazil",
            zipcode = "22222-22",
            pointOfReference = "próximo a um hospital",
            main = false
        )
    ),
    registerDate = LocalDateTime.now(),
    lastRegisterUpdate = LocalDateTime.now()
)


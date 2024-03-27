package com.client.register.mappers

import com.client.register.dtos.ClientDTO
import com.client.register.entities.ClientEntity
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
    address = "Rua 15, Centro",
    purchasePermission = true,
    registerDate = LocalDateTime.now(),
    lastRegisterUpdate = LocalDateTime.now()
)

fun dummyClientEntity() = ClientEntity(
    clientId = 1,
    name = "name",
    email = "email@exemplo.com",
    clientType = ClientType.FISICO,
    documentType = ClientDocumentType.CPF,
    documentNumber = "199.199.199-19",
    address = "Rua 15, Centro",
    purchasePermission = true,
    registerDate = LocalDateTime.now(),
    lastRegisterUpdate = LocalDateTime.now()
)


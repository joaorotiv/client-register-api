package com.client.register.mappers

import com.client.register.dtos.ClientDTO
import com.client.register.entities.ClientEntity
import com.client.register.resources.request.ClientRequest
import com.client.register.resources.response.ClientResponse
import java.time.format.DateTimeFormatter

fun ClientEntity.toDTO() = ClientDTO(
    clientId = clientId,
    name = name,
    email = email,
    clientType = clientType,
    documentType = documentType,
    documentNumber = documentNumber,
    address = address,
    purchasePermission = purchasePermission,
    registerDate = registerDate,
    lastRegisterUpdate = lastRegisterUpdate
)

fun ClientDTO.toResponse() = ClientResponse(
    clientId = clientId!!,
    name = name!!,
    email = email!!,
    clientType = clientType!!,
    documentType = documentType!!,
    documentNumber = documentNumber!!,
    address = address!!,
    purchasePermission = purchasePermission!!,
    registerDate = registerDate!!.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
    lastRegisterUpdate = lastRegisterUpdate!!.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
)

fun ClientRequest.toDTO() = ClientDTO(
    name = name,
    email = email,
    clientType = clientType,
    documentNumber = documentNumber,
    address = address,
    purchasePermission = purchasePermission
)

fun ClientDTO.toEntity() = ClientEntity(
    name = name!!,
    email = email!!,
    clientType = clientType!!,
    documentType = documentType!!,
    documentNumber = documentNumber!!,
    address = address!!,
    purchasePermission = purchasePermission!!,
    registerDate = registerDate,
    lastRegisterUpdate = lastRegisterUpdate
)
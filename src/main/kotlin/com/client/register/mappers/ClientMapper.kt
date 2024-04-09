package com.client.register.mappers

import com.client.register.dtos.ClientDTO
import com.client.register.entities.ClientEntity
import com.client.register.resources.client.request.ClientRequest
import com.client.register.resources.client.response.ClientResponse
import com.client.register.resources.client.response.CreateClientResponse
import java.time.format.DateTimeFormatter

fun ClientEntity.toDTO( ) = ClientDTO(
    clientId = id,
    name = name,
    email = email,
    clientType = clientType,
    documentType = documentType,
    documentNumber = documentNumber,
    addresses = addresses?.map { it.toDTO() }?.toMutableList(),
    purchasePermission = purchasePermission,
    registerDate = registerDate,
    lastRegisterUpdate = lastRegisterUpdate
)

fun ClientDTO.toResponse() = ClientResponse(
    id = clientId!!,
    name = name,
    email = email,
    type = clientType,
    document = (documentType.toString() + ": "  + documentNumber),
    addresses = addresses?.map { it.toResponse() }?.toMutableList(),
    purchasePermission = purchasePermission!!,
    registerDate = registerDate!!.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
    lastRegisterUpdate = lastRegisterUpdate!!.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
)

fun ClientDTO.createToResponse() = CreateClientResponse(
    id = clientId!!,
    name = name,
    email = email,
    type = clientType,
    document = documentNumber,
    registerDate = registerDate!!.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
    lastRegisterUpdate = lastRegisterUpdate!!.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
)

fun ClientRequest.toDTO() = ClientDTO(
    name = name,
    email = email,
    clientType = clientType,
    documentNumber = documentNumber,
    pendingRegistration = pendingRegistration,
    addresses = addresses.map { it.toDTO() }.toMutableList(),
    purchasePermission = purchasePermission
)

fun ClientDTO.toEntity() = ClientEntity(
    name = name,
    email = email,
    clientType = clientType,
    documentType = documentType!!,
    documentNumber = documentNumber,
    purchasePermission = purchasePermission!!,
    pendingRegistration = pendingRegistration!!,
    registerDate = registerDate,
    lastRegisterUpdate = lastRegisterUpdate
)
package com.client.register.mappers

import com.client.register.dtos.AddressDTO
import com.client.register.entities.AddressEntity
import com.client.register.entities.ClientEntity
import com.client.register.resources.address.response.AddressResponse
import com.client.register.resources.address.request.AddressRequest
import com.client.register.resources.address.request.CreateAddressRequest

fun AddressEntity.toDTO() = AddressDTO(
    addressId = id,
    addressType = addressType,
    publicArea = publicArea,
    residenceNumber = residenceNumber,
    complement = complement,
    neighborhood = neighborhood,
    city = city,
    state = state,
    country = country,
    zipcode = zipcode,
    pointOfReference = pointOfReference,
    main = main,
    addressRegisterDate = addressRegisterDate,
    lastAddressUpdate = lastAddressUpdate
)

fun AddressDTO.toResponse() = AddressResponse(
    type = addressType!!,
    fullAddress = ((zipcode + ", " + publicArea + ", " + residenceNumber + ", " +
                    complement + ", " + pointOfReference + ", " + neighborhood + ", " + city + ", " + state + ", " +
                    country)),
    id = addressId!!,
    main = main!!,
)

fun CreateAddressRequest.createToDTO() = AddressDTO(
    publicArea = publicArea,
    residenceNumber = residenceNumber,
    complement = complement,
    neighborhood = neighborhood,
    city = city,
    state = state,
    country = country,
    zipcode = zipcode,
    pointOfReference = pointOfReference,
    main = mainAddress,
)

fun AddressRequest.toDTO() = AddressDTO(
    publicArea = publicArea,
    residenceNumber = residenceNumber,
    complement = complement,
    neighborhood = neighborhood,
    city = city,
    state = state,
    country = country,
    zipcode = zipcode,
    pointOfReference = pointOfReference
)

fun AddressDTO.toEntity(client : ClientEntity) = AddressEntity(
    id = addressId,
    addressType = addressType!!,
    publicArea = publicArea!!,
    residenceNumber = residenceNumber!!,
    complement = complement!!,
    neighborhood = neighborhood!!,
    city = city!!,
    state = state!!,
    country = country!!,
    zipcode = zipcode!!,
    pointOfReference = pointOfReference!!,
    main = main!!,
    client = client,
    addressRegisterDate = addressRegisterDate,
    lastAddressUpdate = lastAddressUpdate
)
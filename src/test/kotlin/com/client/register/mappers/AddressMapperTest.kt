package com.client.register.mappers

import com.client.register.dtos.AddressDTO
import com.client.register.entities.AddressEntity
import com.client.register.enums.AddressType
import java.time.LocalDateTime

fun dummyAddressDTO() = AddressDTO(
    addressId = 1,
    addressType = AddressType.RESIDENCIAL,
    publicArea = "Rua 2-A",
    complement = "Qd 13, lt 1",
    neighborhood = "Setor dos Mestres",
    city = "Rio de Janeiro",
    state = "RJ",
    country = "Brasil",
    zipcode = "14927-55",
    residenceNumber = "S/N",
    pointOfReference = "ao lado de um comercio",
    main = true,
    client = dummyClientDTO(),
    addressRegisterDate = LocalDateTime.now(),
    lastAddressUpdate = LocalDateTime.now()
)

fun dummyAddressEntity() = AddressEntity(
    id = 1,
    addressType = AddressType.RESIDENCIAL,
    publicArea = "Rua 2-A",
    complement = "Qd 13, lt 1, Ap 9",
    neighborhood = "Setor dos Mestres",
    city = "Rio de Janeiro",
    state = "RJ",
    country = "Brasil",
    zipcode = "14927-51",
    residenceNumber = "S/N",
    pointOfReference = "ao lado de um comercio",
    main = true,
    client = dummyClientEntity(),
    addressRegisterDate = LocalDateTime.now(),
    lastAddressUpdate = LocalDateTime.now()
)
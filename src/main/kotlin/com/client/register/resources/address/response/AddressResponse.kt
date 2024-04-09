package com.client.register.resources.address.response

import com.client.register.enums.AddressType

data class AddressResponse (
    val id: Int,
    var type: AddressType,
    val fullAddress: String?,
    val main: Boolean
)
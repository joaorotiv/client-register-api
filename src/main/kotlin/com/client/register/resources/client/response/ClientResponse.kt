package com.client.register.resources.client.response

import com.client.register.enums.ClientType
import com.client.register.resources.address.response.AddressResponse

data class ClientResponse (
    val id: Int,
    var name: String,
    val email: String,
    val type: ClientType,
    val document: String,
    val addresses: MutableList<AddressResponse>? = null,
    val purchasePermission: Boolean,
    val registerDate: String?,
    val lastRegisterUpdate: String?
)
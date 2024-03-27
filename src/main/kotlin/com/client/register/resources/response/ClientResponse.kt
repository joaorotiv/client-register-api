package com.client.register.resources.response

import com.client.register.enums.ClientType
import com.client.register.enums.ClientDocumentType

data class ClientResponse (
    val clientId: Int,
    var name: String,
    val email: String,
    val clientType: ClientType,
    val documentType: ClientDocumentType,
    val documentNumber: String,
    val address: String,
    val purchasePermission: Boolean,
    val registerDate: String?,
    val lastRegisterUpdate: String?
)
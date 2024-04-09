package com.client.register.resources.client.response

import com.client.register.enums.ClientType

data class CreateClientResponse (
    val id: Int,
    var name: String,
    val email: String,
    val type: ClientType,
    val document: String,
    val registerDate: String?,
    val lastRegisterUpdate: String?
)
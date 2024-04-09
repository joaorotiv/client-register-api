package com.client.register.resources.client.request

import com.client.register.enums.ClientType
import com.client.register.resources.address.request.AddressRequest
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class ClientRequest (

    @field:NotBlank
    @field:Size(min=1, max = 30)
    var name: String,

    @field:NotBlank
    @field:Size(min=1, max = 30)
    val email: String,

    @field:NotNull
    val clientType: ClientType,

    @field:NotBlank
    var documentNumber: String,

    val pendingRegistration: Boolean,

    val addresses: MutableList<AddressRequest> = mutableListOf(),

    val purchasePermission: Boolean,
)
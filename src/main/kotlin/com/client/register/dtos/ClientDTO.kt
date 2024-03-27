package com.client.register.dtos

import com.client.register.enums.ClientType
import com.client.register.enums.ClientDocumentType
import java.time.LocalDateTime

data class ClientDTO(
    val clientId: Int? = null,
    var name: String? = null,
    var email: String? = null,
    var clientType: ClientType? = null,
    var documentType: ClientDocumentType? = null,
    var documentNumber: String? = null,
    var address: String? = null,
    var purchasePermission: Boolean? = null,
    val registerDate: LocalDateTime?= null,
    var lastRegisterUpdate: LocalDateTime?= null,
)

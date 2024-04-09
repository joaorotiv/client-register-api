package com.client.register.dtos

import com.client.register.enums.ClientType
import com.client.register.enums.ClientDocumentType
import java.time.LocalDateTime

data class ClientDTO(
    val clientId: Int? = null,
    var name: String,
    var email: String,
    var clientType: ClientType,
    var documentType: ClientDocumentType? = null,
    var documentNumber: String,
    var addresses: MutableList<AddressDTO>? = null,
    var purchasePermission: Boolean? = null,
    var pendingRegistration: Boolean? = null,
    val registerDate: LocalDateTime?= null,
    var lastRegisterUpdate: LocalDateTime?= null,
){

    fun setInitialPermissionsConfig(){
        purchasePermission = false
        pendingRegistration = true
    }
}

package com.client.register.entities

import com.client.register.enums.ClientType
import com.client.register.enums.ClientDocumentType
import jakarta.persistence.*
import lombok.NoArgsConstructor
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@NoArgsConstructor
@Table(name = "client")
data class ClientEntity (

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(name = "client_id")
    val clientId: Int? = null,

    @field:Column
    var name: String,

    @field:Column
    var email: String,

    @field: Column(name = "client_type")
    @field:Enumerated(EnumType.STRING)
    var clientType: ClientType,

    @field:Column(name = "document_type")
    @field:Enumerated(EnumType.STRING)
    var documentType: ClientDocumentType,

    @field:Column(name = "document_number")
    var documentNumber: String,

    @field:Column
    var address: String,

    @field:Column(name = "purchase_permission")
    var purchasePermission: Boolean,

    @field:Column(name = "register_date")
    @field:CreationTimestamp
    var registerDate: LocalDateTime?= null,

    @field:Column(name = "register_update")
    @field:UpdateTimestamp
    var lastRegisterUpdate: LocalDateTime?= null,
    )
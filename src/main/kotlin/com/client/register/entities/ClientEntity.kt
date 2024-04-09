package com.client.register.entities

import com.client.register.consts.Consts.MAX_ADDRESSES_QUANTITY
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
    val id: Int? = null,

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

    @field:OneToMany(mappedBy = "client", cascade = [CascadeType.ALL], orphanRemoval = true)
    @field:Column
    var addresses: List<AddressEntity>? = null,

    @field:Column(name = "purchase_permission")
    var purchasePermission: Boolean,

    @field:Column(name = "pending_registration")
    var pendingRegistration: Boolean,

    @field:Column(name = "register_date")
    @field:CreationTimestamp
    var registerDate: LocalDateTime?= null,

    @field:Column(name = "register_update")
    @field:UpdateTimestamp
    var lastRegisterUpdate: LocalDateTime?= null,
) {
    
    fun maxNumberOfAddresses(): Boolean {
        return addresses!!.size >= MAX_ADDRESSES_QUANTITY
    }

    fun ableToPurchase(){
        this.pendingRegistration = false
        this.purchasePermission = true
    }
}
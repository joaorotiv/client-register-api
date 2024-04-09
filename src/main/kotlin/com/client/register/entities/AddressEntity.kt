package com.client.register.entities

import com.client.register.enums.AddressType
import jakarta.persistence.*
import lombok.NoArgsConstructor
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@NoArgsConstructor
@Table(name = "address")
data class AddressEntity (

    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Column(name = "address_id")
    val id: Int? = null,

    @field:Column(name = "address_type")
    @field:Enumerated(EnumType.STRING)
    var addressType: AddressType,

    @field:Column(name = "public_area")
    var publicArea: String,

    @field: Column(name = "residence_number")
    var residenceNumber: String,

    @field:Column
    var complement: String,

    @field:Column
    var neighborhood: String,

    @field:Column
    var city: String,

    @field:Column
    var state: String,

    @field:Column
    var country: String,

    @field:Column
    var zipcode: String,

    @field:Column(name = "point_of_reference")
    var pointOfReference: String,

    @field:ManyToOne
    @field:JoinColumn(name = "client_id")
    var client: ClientEntity? = null,

    @field:Column(name = "main_address")
    var main: Boolean,

    @field:Column(name = "register_date")
    @field:CreationTimestamp
    var addressRegisterDate: LocalDateTime?= null,

    @field:Column(name = "register_update")
    @field:UpdateTimestamp
    var lastAddressUpdate: LocalDateTime?= null,
){
    fun isFavorite(): Boolean { return this.main }

    fun unfavorite() { this.main = false }

    fun favorite() { this.main = true }
}


package com.client.register.dtos

import com.client.register.enums.AddressType
import java.time.LocalDateTime

data class AddressDTO(
    val addressId: Int? = null,
    var addressType: AddressType? = null,
    var publicArea: String? = null,
    var residenceNumber: String? = null,
    var complement: String? = null,
    var neighborhood: String? = null,
    var city: String? = null,
    var state: String? = null,
    var country: String? = null,
    var zipcode: String? = null,
    var pointOfReference: String? = null,
    var main: Boolean? = null,
    var client: ClientDTO? = null,
    val addressRegisterDate: LocalDateTime?= null,
    var lastAddressUpdate: LocalDateTime?= null,
) {

    fun isFavorite(): Boolean { return this.main!! }
}

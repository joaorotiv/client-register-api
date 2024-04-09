package com.client.register.services.address

import com.client.register.dtos.AddressDTO

interface UpdateAddressService {

    fun updateById(address: AddressDTO, addressId: Int): AddressDTO

    fun favoriteAddress(addressId: Int, clientId: Int)
}
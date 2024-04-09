package com.client.register.services.address

import com.client.register.dtos.AddressDTO

interface GetAddressService {

    fun findAddressById(addressId: Int) : AddressDTO

    fun findAllAddressesFromClient(clientId: Int) : List<AddressDTO>

    fun findAddressByZipcode(zipcode: String, clientId: Int) : List<AddressDTO>
}
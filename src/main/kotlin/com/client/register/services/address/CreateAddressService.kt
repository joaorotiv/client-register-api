package com.client.register.services.address

import com.client.register.dtos.AddressDTO

interface CreateAddressService {

    fun execute(address: AddressDTO, clientId: Int): AddressDTO
}
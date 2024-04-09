package com.client.register.validators.address

import com.client.register.annotations.Validate
import com.client.register.dtos.AddressDTO
import com.client.register.utils.verifyZipCode
import com.client.register.validators.Validator

@Validate
class AddressValidator : Validator<AddressDTO>{

    override fun validate(address: AddressDTO): Boolean {
        verifyZipCode(address)
        return true
    }
}
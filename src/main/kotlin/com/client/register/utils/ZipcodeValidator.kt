package com.client.register.utils

import com.client.register.dtos.AddressDTO
import org.apache.coyote.BadRequestException

fun verifyZipCode(addressDTO: AddressDTO) {
    if (addressDTO.zipcode!!.matches(Regex("\\d{5}-\\d{2}"))) {
    } else {
        throw BadRequestException("CEP inválido! Por favor insira seu cep no seguinte formato: 00000-000")
    }
}

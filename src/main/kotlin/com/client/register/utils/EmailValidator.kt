package com.client.register.utils

import com.client.register.dtos.ClientDTO
import org.apache.coyote.BadRequestException

fun verifyEmail(clientDTO: ClientDTO) {
    if (clientDTO.email.matches(Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+\$"))) {
    } else {
        throw BadRequestException("Email inválido! Por favor insira seu email no seguinte formato: seuemail@exemplo.com")
    }
}

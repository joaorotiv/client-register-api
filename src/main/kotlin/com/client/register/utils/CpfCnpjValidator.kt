package com.client.register.utils

import com.client.register.dtos.ClientDTO
import com.client.register.enums.ClientDocumentType
import com.client.register.enums.ClientType
import org.apache.coyote.BadRequestException

fun verifyValidCpfCnpj(clientDTO: ClientDTO) {
    if (clientDTO.clientType == ClientType.FISICO) {
        clientDTO.documentType = ClientDocumentType.CPF
        if (clientDTO.documentNumber!!.matches(Regex("\\d{3}[.]\\d{3}[.]\\d{3}-\\d{2}"))) {
            println("CPF válido")
        } else {
            throw BadRequestException("CPF inválido! Por favor insira seu CPF no seguinte formato: 000.000.00-00")
        }
    }

    if (clientDTO.clientType == ClientType.JURIDICO) {
        clientDTO.documentType = ClientDocumentType.CNPJ
        if (clientDTO.documentNumber!!.matches(Regex("\\d{2}[.]\\d{3}[.]\\d{3}/\\d{4}-\\d{2}"))) {
            println("CNPJ válido")
        } else {
            throw BadRequestException("CNPJ inválido! Por favor insira seu CNPJ no seguinte formato: 00.000.000/0000-00")
        }
    }
}
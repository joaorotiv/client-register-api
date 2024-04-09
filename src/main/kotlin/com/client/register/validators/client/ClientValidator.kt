package com.client.register.validators.client

import com.client.register.annotations.Validate
import com.client.register.dtos.ClientDTO
import com.client.register.utils.verifyEmail
import com.client.register.utils.verifyAndSetClientDocumentType
import com.client.register.validators.Validator

@Validate
class ClientValidator : Validator<ClientDTO>{

    override fun validate(client: ClientDTO) : Boolean {
        verifyAndSetClientDocumentType(client)
        verifyEmail(client)
        return true
    }
}
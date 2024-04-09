package com.client.register.services.client

import com.client.register.dtos.ClientDTO

interface CreateClientService {

    fun create(client: ClientDTO): ClientDTO
}
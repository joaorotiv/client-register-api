package com.client.register.services

import com.client.register.dtos.ClientDTO

interface CreateClientService {

    fun execute(clientDTO: ClientDTO): ClientDTO
}
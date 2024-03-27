package com.client.register.services

import com.client.register.dtos.ClientDTO
import org.springframework.data.domain.Page

interface GetClientService {

    fun findClientById(clientId: Int) : ClientDTO

    fun findClientByName(name: String) : List<ClientDTO>

    fun findFilteredClients(page: Int, size: Int, orderBy: String, direction: String): Page<ClientDTO>
}
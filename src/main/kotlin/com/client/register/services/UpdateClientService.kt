package com.client.register.services

import com.client.register.dtos.ClientDTO

interface UpdateClientService {

    fun updateById(clientDTO: ClientDTO, clientId: Int): ClientDTO

    fun updateClientPermissionById(purchasePermission: Boolean, clientId: Int)
}
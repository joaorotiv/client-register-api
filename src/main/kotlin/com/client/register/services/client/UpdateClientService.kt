package com.client.register.services.client

import com.client.register.dtos.ClientDTO

interface UpdateClientService {

    fun updateById(client: ClientDTO, clientId: Int): ClientDTO

    fun updateClientPermissionById(purchasePermission: Boolean, clientId: Int)
}
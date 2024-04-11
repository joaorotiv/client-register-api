package com.client.register.services.client

import com.client.register.helper.ClientApiHelper
import com.client.register.dtos.ClientDTO
import com.client.register.mappers.toDTO
import com.client.register.repositories.ClientRepository
import com.client.register.validators.Validator
import mu.two.KotlinLogging
import org.springframework.beans.BeanUtils
import org.springframework.stereotype.Service

@Service
class ClientUpdaterService (
    private val repository: ClientRepository,
    private val validator: Validator<ClientDTO>,
    private val helper: ClientApiHelper
): UpdateClientService {

    private val log = KotlinLogging.logger {}

    override fun updateById(client: ClientDTO, clientId: Int): ClientDTO {
        log.info { "=M ClientUpdaterService.updateById(), stage=init, clientId${clientId}" }
        validator.validate(client)

        val saved = helper.findClientOrThrow(clientId)

        BeanUtils.copyProperties(client, saved,"registerDate")

        log.info { "=M ClientUpdaterService.updateById(), stage=finish, clientId${clientId}" }
        return repository.save(saved).toDTO()
    }

    override fun updateClientPermissionById(purchasePermission: Boolean, clientId: Int) {
        log.info { "=M ClientUpdaterService.updateClientPermissionById(), stage=init, clientId${clientId}" }

        val saved = helper.findClientOrThrow(clientId)

        saved.purchasePermission = purchasePermission

        repository.updateClientPermissionById(clientId, purchasePermission)

        log.info { "=M ClientUpdaterService.updateClientPermissionById(), stage=finish, clientId${clientId}" }
    }
}
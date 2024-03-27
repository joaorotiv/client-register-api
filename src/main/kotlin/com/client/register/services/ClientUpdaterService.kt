package com.client.register.services

import com.client.register.dtos.ClientDTO
import com.client.register.mappers.toDTO
import com.client.register.repositories.UpdateClientRepository
import com.client.register.utils.verifyEmail
import com.client.register.utils.verifyValidCpfCnpj
import mu.two.KotlinLogging
import org.springframework.beans.BeanUtils
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import javax.inject.Named

@Named
class ClientUpdaterService (
    private val repository: UpdateClientRepository,
): UpdateClientService {

    private val log = KotlinLogging.logger {}

    override fun updateById(clientDTO: ClientDTO, clientId: Int): ClientDTO {
        log.info { "=M ClientUpdaterService.updateById(), stage=init, clientId${clientId}" }
        verifyEmail(clientDTO)
        verifyValidCpfCnpj(clientDTO)

        val savedClient = repository.findById(clientId)
            .orElseThrow { NotFoundException() }

        BeanUtils.copyProperties(clientDTO, savedClient,"registerDate")

        val done = repository.save(savedClient).toDTO()

        log.info { "=M ClientUpdaterService.updateById(), stage=finish, clientId${clientId}" }
        return done
    }

    override fun updateClientPermissionById(purchasePermission: Boolean, clientId: Int) {
        log.info { "=M ClientUpdaterService.updateClientPermissionById(), stage=init, clientId${clientId}" }

        val savedClient = repository.findById(clientId)
            .orElseThrow { NotFoundException() }

        savedClient.purchasePermission = purchasePermission

        repository.updateClientPermissionById(clientId, purchasePermission)

        log.info { "=M ClientUpdaterService.updateClientPermissionById(), stage=finish, clientId${clientId}" }
    }
}
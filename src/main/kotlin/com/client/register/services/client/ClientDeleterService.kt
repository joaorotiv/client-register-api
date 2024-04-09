package com.client.register.services.client

import com.client.register.repositories.ClientRepository
import mu.two.KotlinLogging
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

@Service
class ClientDeleterService (
    private val repository: ClientRepository
): DeleteClientService {

    private val log = KotlinLogging.logger {}

    override fun delete(clientId: Int) {
        log.info { "M=ClientDeleterService.delete, stage=init, clientId${clientId}"}
        val saved = repository.findById(clientId)
                              .orElseThrow { NotFoundException() }

        repository.deleteById(saved.id!!)
        log.info { "M=ClientDeleterService.delete, stage=finish, clientId${clientId}"}
    }
}
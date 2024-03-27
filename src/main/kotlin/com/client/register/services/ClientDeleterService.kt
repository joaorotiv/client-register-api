package com.client.register.services

import com.client.register.repositories.DeleteClientRepository
import mu.two.KotlinLogging
import javax.inject.Named
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException

@Named
class ClientDeleterService (
    private val repository: DeleteClientRepository
): DeleteClientService {

    private val log = KotlinLogging.logger {}

    override fun delete(clientId: Int) {
        log.info { "M=ClientDeleterService.delete, stage=init, clientId${clientId}"}
        val savedClient = repository.findById(clientId)
            .orElseThrow { NotFoundException() }

        repository.deleteById(savedClient.clientId!!)
        log.info { "M=ClientDeleterService.delete, stage=finish, clientId${clientId}"}
    }
}
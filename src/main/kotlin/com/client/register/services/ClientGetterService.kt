package com.client.register.services

import com.client.register.dtos.ClientDTO
import com.client.register.exceptions.EmptyListException
import com.client.register.mappers.toDTO
import com.client.register.repositories.GetClientRepository
import mu.two.KotlinLogging
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import javax.inject.Named

@Named
class ClientGetterService(
    private val repository: GetClientRepository
): GetClientService {

    private val log = KotlinLogging.logger {}

    override fun findClientById(clientId: Int) : ClientDTO{
        log.info { "M=ClientGetterService.findClientById, stage=init, clientId${clientId}"}
        val done = repository.findById(clientId)
            .orElseThrow { NotFoundException() }

        log.info { "M=ClientGetterService.findClientById, stage=finish, clientId${clientId}"}
        return done.toDTO()
    }

    override fun findClientByName(name: String) : List<ClientDTO>{
        log.info { "M=ClientGetterService.findClientByName, stage=init, name${name}"}
        val done = repository.findClientByName(name).map { it.toDTO() }
            .ifEmpty { throw EmptyListException("") }

        log.info { "M=ClientGetterService.findClientByName, stage=finish, name${name}"}
        return done
    }

    override fun findFilteredClients(page: Int, size: Int, orderBy: String, direction: String): Page<ClientDTO> {
        log.info { "M=ClientGetterService.findFilteredClients, stage=init"}
        val pageRequest = PageRequest.of(
            page,
            size,
            Sort.Direction.valueOf(direction),
            orderBy
        )

        log.info { "M=ClientGetterService.findFilteredClients, stage=finish"}
        return repository.findAll(pageRequest).map { it.toDTO() }
    }
}
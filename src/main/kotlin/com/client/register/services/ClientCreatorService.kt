package com.client.register.services

import com.client.register.dtos.ClientDTO
import com.client.register.mappers.toDTO
import com.client.register.mappers.toEntity
import com.client.register.repositories.CreateClientRepository
import com.client.register.utils.verifyEmail
import com.client.register.utils.verifyValidCpfCnpj
import mu.two.KotlinLogging
import javax.inject.Named

@Named
class ClientCreatorService(
    private val repository: CreateClientRepository
): CreateClientService {

    private val log = KotlinLogging.logger {}

    override fun execute(clientDTO: ClientDTO): ClientDTO {
        log.info { "M=ClientCreatorService.execute, stage=init" }
        verifyEmail(clientDTO)
        verifyValidCpfCnpj(clientDTO)

        val done = repository.save(clientDTO.toEntity())

        log.info { "M=ClientCreatorService.execute, stage=finish" }
        return done.toDTO()
    }
}



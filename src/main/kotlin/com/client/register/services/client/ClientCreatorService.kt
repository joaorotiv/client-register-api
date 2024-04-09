package com.client.register.services.client

import com.client.register.dtos.ClientDTO
import com.client.register.mappers.toDTO
import com.client.register.mappers.toEntity
import com.client.register.repositories.ClientRepository
import com.client.register.validators.Validator
import mu.two.KotlinLogging
import org.springframework.stereotype.Service

@Service
class ClientCreatorService(
    private val repository: ClientRepository,
    private val validator: Validator<ClientDTO>
): CreateClientService {

    private val log = KotlinLogging.logger {}

    override fun create(client: ClientDTO): ClientDTO {
        log.info { "M=ClientCreatorService.execute, stage=init" }
        validator.validate(client)
        client.setInitialPermissionsConfig()

        val saved = repository.save(client.toEntity())

        log.info { "M=ClientCreatorService.execute, stage=finish" }
        return saved.toDTO()
    }
}



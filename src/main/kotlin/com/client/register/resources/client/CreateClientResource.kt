package com.client.register.resources.client

import com.client.register.mappers.createToResponse
import com.client.register.mappers.toDTO
import com.client.register.resources.client.request.ClientRequest
import com.client.register.resources.client.response.CreateClientResponse
import com.client.register.services.client.CreateClientService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import mu.two.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/client")
class CreateClientResource(
    private val createClientService: CreateClientService,
) {
    private val log = KotlinLogging.logger {}

    @Operation(summary = "Create client", description = "Método para criar um cliente")
    @PostMapping
    @ApiResponse(description = "Cliente cadastrado com sucesso.", responseCode = "201")
    @ApiResponse(description = "Erro na requisição.", responseCode = "400")
    @ApiResponse(description = "Erro no sistema.", responseCode = "500")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid clientRequest: ClientRequest): CreateClientResponse {
        log.info { "M=CreateClientResource.create(), stage=init" }
        val done = createClientService.create(clientRequest.toDTO())

        log.info { "M=CreateClientResource.create(), stage=finish" }
        return done.createToResponse()
    }
}
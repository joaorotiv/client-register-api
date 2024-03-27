package com.client.register.resources

import com.client.register.mappers.toDTO
import com.client.register.mappers.toResponse
import com.client.register.resources.request.ClientRequest
import com.client.register.resources.response.ClientResponse
import com.client.register.services.CreateClientService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import mu.two.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/client")
class CreateClientResource(
    private val createClientService: CreateClientService
){
    private val log = KotlinLogging.logger {}

    @Operation(summary = "Create client", description = "Método para criar um cliente")
    @PostMapping
    @ApiResponse(description = "Cliente cadastrado com sucesso.", responseCode = "201")
    @ApiResponse(description = "Erro na requisição.", responseCode = "400")
    @ApiResponse(description = "Erro no sistema.", responseCode = "500")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid clientRequest: ClientRequest): ClientResponse {
        log.info { "M=CreateClientResource.create(), stage=init" }
        val done = createClientService.execute(clientRequest.toDTO())

        return done.toResponse()
    }
}
package com.client.register.resources

import com.client.register.mappers.toResponse
import com.client.register.resources.response.ClientResponse
import com.client.register.services.GetClientService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import mu.two.KotlinLogging
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/client")
class GetClientResource(
    private val service: GetClientService
){

    private val log = KotlinLogging.logger {}

    @Operation(summary = "Get client by Id", description = "Método para achar um cliente por id e retorná-lo")
    @GetMapping("/{clientId}")
    @ApiResponse(description = "Cliente encontrado com sucesso.", responseCode = "200")
    @ApiResponse(description = "Erro na requisição.", responseCode = "400")
    @ApiResponse(description = "Cliente não encontrado.", responseCode = "404")
    @ApiResponse(description = "Erro no sistema.", responseCode = "500")
    @ResponseStatus(HttpStatus.OK)
    fun getClientById(@PathVariable clientId: Int) : ClientResponse{
        log.info { "=M GetClientResource.getClientById(), stage=init, clientId${clientId}"}
        return service.findClientById(clientId).toResponse()
    }

    @Operation(summary = "Get client by name", description = "Método para achar um cliente por nome e retorná-lo")
    @GetMapping
    @ApiResponse(description = "Cliente encontrado com sucesso.", responseCode = "200")
    @ApiResponse(description = "Erro na requisição.", responseCode = "400")
    @ApiResponse(description = "Cliente não encontrado.", responseCode = "404")
    @ApiResponse(description = "Erro no sistema.", responseCode = "500")
    @ResponseStatus(HttpStatus.OK)
    fun getClientByName(@RequestParam name: String) : List<ClientResponse>{
        log.info { "=M GetClientResource.getClientById(), stage=init, name${name}"}
        return service.findClientByName(name).map { it.toResponse() }
    }

    @Operation(summary = "Get filtered list of clients", description = "Método para listar clientes filtados e paginados")
    @GetMapping("/filtered")
    @ApiResponse(description = "Clientes encontrados com sucesso", responseCode = "200")
    @ApiResponse(description = "Clientes não econtrados", responseCode = "404")
    @ApiResponse(description = "Erro no sistema", responseCode = "500")
    fun getFilteredClients(
        @RequestParam(value = "page", defaultValue = "0") page: Int,
        @RequestParam(value = "size", defaultValue = "3") size: Int,
        @RequestParam(value = "orderBy", defaultValue = "name") orderBy: String,
        @RequestParam(value = "direction", defaultValue = "ASC") direction: String
    ): ResponseEntity<Page<ClientResponse>> {
        log.info { "=M GetClientResource.getFilteredClients(), stage=init"}
        return ResponseEntity.ok().body(service.findFilteredClients(page, size, orderBy, direction).map { it.toResponse() })
    }
}
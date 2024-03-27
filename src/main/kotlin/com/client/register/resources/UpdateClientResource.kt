package com.client.register.resources

import com.client.register.mappers.toDTO
import com.client.register.mappers.toResponse
import com.client.register.resources.request.ClientRequest
import com.client.register.resources.response.ClientResponse
import com.client.register.services.UpdateClientService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import mu.two.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/client")
class UpdateClientResource (
    private val service: UpdateClientService
){

    private val log = KotlinLogging.logger {}

    @Operation(summary = "Update client by Id", description = "Método para atualizar o cadastro do cliente.")
    @PutMapping("/{clientId}")
    @ApiResponse(description = "Cliente atualizado com sucesso.", responseCode = "200")
    @ApiResponse(description = "Erro na requisição.", responseCode = "400")
    @ApiResponse(description = "Cliente não encontrado.", responseCode = "404")
    @ApiResponse(description = "Erro no sistema.", responseCode = "500")
    @ResponseStatus(HttpStatus.OK)
    fun updateClientById(@PathVariable clientId: Int, @RequestBody @Valid clientRequest: ClientRequest): ClientResponse{
        log.info { "=M UpdateClientResource.updateClient(), stage=init, clientId${clientId}"}
        val done = service.updateById(clientRequest.toDTO(), clientId)

        log.info { "=M UpdateClientResource.updateClient(), stage=finish, clientId${clientId}"}
        return done.toResponse()
    }

    @Operation(summary = "Update client permission by Id", description = "Método para atualizar a permissão de compra do cliente")
    @PatchMapping("/{clientId}")
    @ApiResponse(description = "Permissão do cliente alterada com sucesso.", responseCode = "200")
    @ApiResponse(description = "Erro na requisição.", responseCode = "400")
    @ApiResponse(description = "Cliente não encontrado.", responseCode = "404")
    @ApiResponse(description = "Erro no sistema.", responseCode = "500")
    @ResponseStatus(HttpStatus.OK)
    fun updateClientPermissionById(@PathVariable clientId: Int, @RequestParam @Valid purchasePermission: Boolean) {
        log.info { "=M UpdateClientResource.updateClientPermissionById(), stage=init, clientIdv${clientId}"}
        service.updateClientPermissionById(purchasePermission, clientId)

        log.info { "=M UpdateClientResource.updateClientPermissionById(), stage=finish, clientId${clientId}"}
    }

}
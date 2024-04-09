package com.client.register.resources.client

import com.client.register.services.client.DeleteClientService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import mu.two.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/client")
class DeleteClientResource(
    private val service: DeleteClientService,
){
    private val log = KotlinLogging.logger {}

    @Operation(summary = "Delete client by Id", description = "Método para deletar um cliente por id")
    @DeleteMapping("/{clientId}")
    @ApiResponse(description = "Cliente deletado com sucesso.", responseCode = "200")
    @ApiResponse(description = "Erro na requisição.", responseCode = "400")
    @ApiResponse(description = "Cliente não encontrado ou já deletado.", responseCode = "404")
    @ApiResponse(description = "Erro no sistema.", responseCode = "500")
    @ResponseStatus(HttpStatus.OK)
    fun deleteById(@PathVariable clientId: Int): String{
        log.info { "M=DeleteClientResource.deleteById(), stage=init, clientId${clientId}" }
        service.delete(clientId)

        log.info { "M=DeleteClientResource.deleteById(), stage=finish, clientId${clientId}"}
        return ("Cliente deletado com sucesso!")
    }
}

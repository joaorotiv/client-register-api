package com.client.register.resources.address

import com.client.register.services.address.DeleteAddressService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import mu.two.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/address")
class DeleteAddressResource(
    private val service: DeleteAddressService
){
    private val log = KotlinLogging.logger {}

    @Operation(summary = "Delete address by id", description = "Método para deletar um endereço por id")
    @DeleteMapping("/{addressId}")
    @ApiResponse(description = "Endereço deletado com sucesso.", responseCode = "201")
    @ApiResponse(description = "Erro na requisição.", responseCode = "400")
    @ApiResponse(description = "Erro no sistema.", responseCode = "500")
    @ResponseStatus(HttpStatus.OK)
    fun delete(@PathVariable addressId: Int): String{
        log.info { "M=DeleteAddressResource.delete(), stage=init, addressId${addressId}" }
        service.deleteAddressById(addressId)

        log.info { "M=DeleteAddressResource.delete(), stage=finish, addressId${addressId}"}
        return ("Endereço deletado com sucesso!")
    }
}

package com.client.register.resources.address

import com.client.register.mappers.toResponse
import com.client.register.mappers.createToDTO
import com.client.register.resources.address.response.AddressResponse
import com.client.register.resources.address.request.CreateAddressRequest
import com.client.register.services.address.CreateAddressService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import mu.two.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/address")
class CreateAddressResource(
    private val createAddressService: CreateAddressService
){
    private val log = KotlinLogging.logger {}

    @Operation(summary = "Create address for client", description = "Método para criar um endereço para determinado cliente")
    @PostMapping("/{clientId}")
    @ApiResponse(description = "Endereço cadastrado com sucesso.", responseCode = "201")
    @ApiResponse(description = "Erro na requisição.", responseCode = "400")
    @ApiResponse(description = "Erro no sistema.", responseCode = "500")
    @ResponseStatus(HttpStatus.CREATED)
    fun createAddress(@PathVariable clientId: Int, @RequestBody address: CreateAddressRequest): AddressResponse {
        log.info { "M=CreateAddressResource.createAddress(), stage=init, clientId=$clientId" }
        val done = createAddressService.execute(address.createToDTO(), clientId)

        log.info { "M=CreateAddressResource.createAddress(), stage=finish, clientId=$clientId" }
        return done.toResponse()
    }
}
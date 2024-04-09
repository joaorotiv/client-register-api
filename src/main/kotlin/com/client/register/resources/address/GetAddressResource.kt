package com.client.register.resources.address

import com.client.register.mappers.toResponse
import com.client.register.resources.address.response.AddressResponse
import com.client.register.services.address.GetAddressService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import mu.two.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/address")
class GetAddressResource(
    private val service: GetAddressService
){
    private val log = KotlinLogging.logger {}

    @Operation(summary = "Get address by Id", description = "Método para achar um endereço cadastrado por id de determinado cliente e retorná-lo")
    @GetMapping("/{clientId}/{addressId}")
    @ApiResponse(description = "Endereço encontrado com sucesso.", responseCode = "200")
    @ApiResponse(description = "Erro na requisição.", responseCode = "400")
    @ApiResponse(description = "Endereço não encontrado.", responseCode = "404")
    @ApiResponse(description = "Erro no sistema.", responseCode = "500")
    @ResponseStatus(HttpStatus.OK)
    fun getAddressById(@PathVariable addressId: Int, @PathVariable clientId: Int) : AddressResponse {
        log.info { "=M GetAddressResource.getAddressById(), stage=init, addressId${addressId}"}
        return service.findAddressById(addressId).toResponse()
    }

    @Operation(summary = "Get all address from client", description = "Método para achar todos os endereços cadastrados de determinado cliente")
    @GetMapping("/{clientId}/all")
    @ApiResponse(description = "Endereços encontrados com sucesso.", responseCode = "200")
    @ApiResponse(description = "Erro na requisição.", responseCode = "400")
    @ApiResponse(description = "Endereços não encontrados.", responseCode = "404")
    @ApiResponse(description = "Erro no sistema.", responseCode = "500")
    @ResponseStatus(HttpStatus.OK)
    fun getAllAddressesFromClient(@PathVariable clientId: Int) : List<AddressResponse>{
        log.info { "=M GetAddressResource.getAllAddressesFromClient(), stage=init, clientId${clientId}"}
        return service.findAllAddressesFromClient(clientId).map { it.toResponse() }
    }

    @Operation(summary = "Get address by zipcode", description = "Método para achar um endereço de determinado cliente por CEP e retorná-lo")
    @GetMapping("/{clientId}")
    @ApiResponse(description = "Endereço encontrado com sucesso.", responseCode = "200")
    @ApiResponse(description = "Erro na requisição.", responseCode = "400")
    @ApiResponse(description = "Endereço não encontrado.", responseCode = "404")
    @ApiResponse(description = "Erro no sistema.", responseCode = "500")
    @ResponseStatus(HttpStatus.OK)
    fun getAddresByZipcode(@PathVariable clientId: Int, @RequestParam zipcode: String) : List<AddressResponse>{
        log.info { "=M GetAddressResource.getAddresByZipcode(), stage=init, clientId${clientId}"}
        return service.findAddressByZipcode(zipcode, clientId).map { it.toResponse() }
    }
}
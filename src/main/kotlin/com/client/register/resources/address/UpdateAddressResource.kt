package com.client.register.resources.address

import com.client.register.mappers.toDTO
import com.client.register.mappers.toResponse
import com.client.register.resources.address.response.AddressResponse
import com.client.register.resources.address.request.AddressRequest
import com.client.register.services.address.UpdateAddressService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import mu.two.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/address")
class UpdateAddressResource (
    private val service: UpdateAddressService
) {
    private val log = KotlinLogging.logger {}

    @Operation(summary = "Update address by Id", description = "Método para atualizar o endereço de determinado cliente.")
    @PutMapping("/{clientId}/{addressId}")
    @ApiResponse(description = "Endereço atualizado com sucesso.", responseCode = "200")
    @ApiResponse(description = "Erro na requisição.", responseCode = "400")
    @ApiResponse(description = "Endereço não encontrado.", responseCode = "404")
    @ApiResponse(description = "Erro no sistema.", responseCode = "500")
    @ResponseStatus(HttpStatus.OK)
    fun updateById(@PathVariable addressId: Int, @RequestBody @Valid addressRequest: AddressRequest): AddressResponse {
        log.info { "=M UpdateAddressResource.updateById(), stage=init, addressId${addressId}"}
        val done = service.updateById(addressRequest.toDTO(), addressId)

        log.info { "=M UpdateAddressResource.updateById(), stage=finish, addressId${addressId}"}
        return done.toResponse()
    }

    @Operation(summary = "Set new favorite address", description = "Método para selecionar o endereço principal dentre os endereços cadastrados")
    @PatchMapping("/{clientId}/{addressId}")
    @ApiResponse(description = "Endereço principal alterado com sucesso.", responseCode = "200")
    @ApiResponse(description = "Erro na requisição.", responseCode = "400")
    @ApiResponse(description = "Endereço de cliente não encontrado.", responseCode = "404")
    @ApiResponse(description = "Erro no sistema.", responseCode = "500")
    @ResponseStatus(HttpStatus.OK)
    fun favoriteNewAddress(@PathVariable addressId: Int, @PathVariable clientId: Int) {
        log.info { "=M UpdateAddressResource.favoriteNewAddress(), stage=init, addressId${addressId}"}
        service.favoriteAddress(addressId, clientId)

        log.info { "=M UpdateAddressResource.favoriteNewAddress(), stage=finish, addressId${addressId}"}
    }
}
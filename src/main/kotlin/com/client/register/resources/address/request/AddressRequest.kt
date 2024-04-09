package com.client.register.resources.address.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class AddressRequest (

    @field:NotBlank
    @field:Size(min=1, max = 30)
    var publicArea: String,

    @field:NotBlank
    @field:Size(min=1, max = 30)
    val residenceNumber: String,

    @field:NotBlank
    @field:Size(min=1, max = 30)
    val complement: String,

    @field:NotBlank
    @field:Size(min=1, max = 30)
    var neighborhood: String,

    @field:NotBlank
    @field:Size(min=1, max = 30)
    val city: String,

    @field:NotBlank
    @field:Size(min=1, max = 30)
    val state: String,

    @field:NotBlank
    @field:Size(min=1, max = 30)
    val country: String,

    @field:NotBlank
    @field:Size(min=1, max = 30)
    val zipcode: String,

    @field:NotBlank
    @field:Size(min=1, max= 30)
    val pointOfReference: String
)
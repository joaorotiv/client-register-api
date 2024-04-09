package com.client.register.resources.address

import com.client.register.configs.Setup
import com.github.database.rider.core.api.dataset.DataSet
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class CreateAddressResourceTest: Setup() {

    override fun basePath(): String = "/v1/address"

    @Test
    @DataSet(value = ["datasets/create-client.yaml"])
    fun `should successfully create client`() {
        Given {
            spec(requestSpecification)
            body(
                "{\"publicArea\":\"Rua 1-B\", \"residenceNumber\":\"S/N\", \"complement\":\"Qd 13, Lt 3\", \"neighborhood\":\"Bairro das Garças\", \"city\":\"São Paulo\", \"state\":\"SP\", \"country\":\"Brasil\", \"zipcode\":\"82956-10\", \"pointOfReference\":\"próximo a um hospital\", \"mainAddress\": \"true\"}"
            )
        } When {
            post("/2")
        } Then {
            statusCode(HttpStatus.CREATED.value())
        }
    }

    @Test
    @DataSet(value = ["datasets/create-client.yaml"])
    fun `should throw badRequest while trying to address client with wrong request`() {
        Given {
            spec(requestSpecification)
            body("{\"publicArea\":\"Rua 1-B\"}")
        } When {
            post("/2")
        } Then {
            statusCode(HttpStatus.BAD_REQUEST.value())
        }
    }
}
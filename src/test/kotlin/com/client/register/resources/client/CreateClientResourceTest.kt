package com.client.register.resources.client

import com.client.register.configs.Setup
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class CreateClientResourceTest: Setup() {

    override fun basePath(): String = "/v1/client"

    @Test
    fun `should successfully create client`() {
        Given {
            spec(requestSpecification)
            body(
                "{\"name\": \"Jorge\", \"email\":\"emailjorge@gmail.com\", \"clientType\":\"FISICO\", \"documentNumber\":\"199.999.999-20\", \"addresses\":[{\"addressType\":\"RESIDENCIAL\", \"publicArea\":\"Rua 1-B\", \"residenceNumber\":\"S/N\", \"complement\":\"Qd 13, Lt 3\", \"neighborhood\":\"Bairro das Garças\", \"city\":\"São Paulo\", \"state\":\"SP\", \"country\":\"Brasil\", \"zipcode\":\"82956-10\", \"pointOfReference\":\"próximo a um hospital\"}]}"
            )
        } When {
            post("")
        } Then {
            statusCode(HttpStatus.CREATED.value())
            body("id", equalTo(1))
            body("name", equalTo("Jorge"))
        }
    }

    @Test
    fun `should throw badRequest while trying to create client with wrong request`() {
        Given {
            spec(requestSpecification)
            body("{\"clientId\":\"1\"}")
        } When {
            post("/")
        } Then {
            statusCode(HttpStatus.BAD_REQUEST.value())
        }
    }
}
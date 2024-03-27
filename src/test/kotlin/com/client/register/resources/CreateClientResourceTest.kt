package com.client.register.resources

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
                "{\"name\": \"Jorge\", \"email\":\"email@gmail.com\", \"clientType\":\"FISICO\", \"documentNumber\":\"199.999.999-20\", \"address\":\"Rua 10, Centro\", \"address\":\"Rua 10, Centro\", \"purchasePermission\":\"true\"}"
            )
        } When {
            post("")
        } Then {
            statusCode(HttpStatus.CREATED.value())
            body("clientId", equalTo(1))
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

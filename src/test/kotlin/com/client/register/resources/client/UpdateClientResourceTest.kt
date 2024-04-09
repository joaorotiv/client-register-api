package com.client.register.resources.client

import com.client.register.configs.Setup
import com.github.database.rider.core.api.dataset.DataSet
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class UpdateClientResourceTest: Setup() {

    override fun basePath(): String = "/v1/client"

    @Test
    @DataSet(value = ["datasets/create-client.yaml"])
    fun `should successfully update client`() {
        Given {
            spec(requestSpecification)
            body(
                "{\"name\": \"Jorge\", \"email\":\"emailjorge@gmail.com\", \"clientType\":\"FISICO\", \"documentNumber\":\"199.999.999-20\", \"addresses\":[{\"addressType\":\"RESIDENCIAL\", \"publicArea\":\"Rua 1-B\", \"residenceNumber\":\"S/N\", \"complement\":\"Qd 13, Lt 3\", \"neighborhood\":\"Bairro das Garças\", \"city\":\"São Paulo\", \"state\":\"SP\", \"country\":\"Brasil\", \"zipcode\":\"82956-10\", \"pointOfReference\":\"próximo a um hospital\"}]}"
            )
        } When {
            put("/1")
        } Then {
            statusCode(HttpStatus.OK.value())
            body("id", CoreMatchers.equalTo(1))
            body("name", CoreMatchers.equalTo("Jorge"))
        }
    }

    @Test
    @DataSet(value = ["datasets/create-client.yaml"])
    fun `should successfully update client purchase permission`() {
        Given {
            spec(requestSpecification)
        } When {
            patch("/1?purchasePermission=false")
        } Then {
            statusCode(HttpStatus.OK.value())
        }
    }

    @Test
    @DataSet(value = ["datasets/create-client.yaml"])
    fun `should throw notFound while trying to update non-existent client`() {
        Given {
            spec(requestSpecification)
            body(
                "{\"name\": \"Jorge\", \"email\":\"emailjorge@gmail.com\", \"clientType\":\"FISICO\", \"documentNumber\":\"199.999.999-20\", \"addresses\":[{\"addressType\":\"RESIDENCIAL\", \"publicArea\":\"Rua 1-B\", \"residenceNumber\":\"S/N\", \"complement\":\"Qd 13, Lt 3\", \"neighborhood\":\"Bairro das Garças\", \"city\":\"São Paulo\", \"state\":\"SP\", \"country\":\"Brasil\", \"zipcode\":\"82956-10\", \"pointOfReference\":\"próximo a um hospital\"}]}"
            )
        } When {
            put("/999")
        } Then {
            statusCode(HttpStatus.NOT_FOUND.value())
        }
    }

    @Test
    @DataSet(value = ["datasets/create-client.yaml"])
    fun `should throw notFound while trying to update purchase permission from non-existent client`() {
        Given {
            spec(requestSpecification)
        } When {
            patch("/100?purchasePermission=false")
        } Then {
            statusCode(HttpStatus.NOT_FOUND.value())
        }
    }

    @Test
    @DataSet(value = ["datasets/create-client.yaml"])
    fun `should throw badRequest while trying to update client with wrong request`() {
        Given {
            spec(requestSpecification)
            body("{\"clientId\":\"1\"}")
        } When {
            put("")
        } Then {
            statusCode(HttpStatus.BAD_REQUEST.value())
        }
    }

    @Test
    @DataSet(value = ["datasets/create-client.yaml"])
    fun `should throw badRequest while trying to update purchase permission from non-existent client`() {
        Given {
            spec(requestSpecification)
        } When {
            patch("/1?purchasePermission")
        } Then {
            statusCode(HttpStatus.BAD_REQUEST.value())
        }
    }
}

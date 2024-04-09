package com.client.register.resources.address

import com.client.register.configs.Setup
import com.github.database.rider.core.api.dataset.DataSet
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class UpdateAddressResourceTest: Setup() {

    override fun basePath(): String = "/v1/address"

    @Test
    @DataSet(value = ["datasets/create-address.yaml"])
    fun `should successfully update address by id`() {
        Given {
            spec(requestSpecification)
            body(
                "{\"publicArea\":\"Rua 1-B\", \"residenceNumber\":\"S/N\", \"complement\":\"Qd 13, Lt 3\", \"neighborhood\":\"Bairro das Garças\", \"city\":\"São Paulo\", \"state\":\"SP\", \"country\":\"Brasil\", \"zipcode\":\"82956-10\", \"pointOfReference\":\"próximo a um hospital\", \"mainAddress\": \"true\"}"
            )
        } When {
            put("/1/2")
        } Then {
            statusCode(HttpStatus.OK.value())
        }
    }

    @Test
    @DataSet(value = ["datasets/create-address.yaml"])
    fun `should successfully update favorite address`() {
        Given {
            spec(requestSpecification)
        } When {
            patch("/1/2")
        } Then {
            statusCode(HttpStatus.OK.value())
        }
    }

    @Test
    @DataSet(value = ["datasets/create-address.yaml"])
    fun `should throw notFound while trying to update non-existent address`() {
        Given {
            spec(requestSpecification)
            body(
                "{\"publicArea\":\"Rua 1-B\", \"residenceNumber\":\"S/N\", \"complement\":\"Qd 13, Lt 3\", \"neighborhood\":\"Bairro das Garças\", \"city\":\"São Paulo\", \"state\":\"SP\", \"country\":\"Brasil\", \"zipcode\":\"82956-10\", \"pointOfReference\":\"próximo a um hospital\", \"mainAddress\": \"true\"}"
            )
        } When {
            put("/1/999")
        } Then {
            statusCode(HttpStatus.NOT_FOUND.value())
        }
    }

    @Test
    @DataSet(value = ["datasets/create-address.yaml"])
    fun `should throw notFound while trying to update favorite address to a non-existent address`() {
        Given {
            spec(requestSpecification)
        } When {
            patch("/1/999")
        } Then {
            statusCode(HttpStatus.NOT_FOUND.value())
        }
    }

    @Test
    @DataSet(value = ["datasets/create-address.yaml"])
    fun `should throw badRequest while trying to update address with wrong request`() {
        Given {
            spec(requestSpecification)
            body("{\"publicArea\":\"Rua 1-B\"}")
        } When {
            put("")
        } Then {
            statusCode(HttpStatus.BAD_REQUEST.value())
        }
    }

    @Test
    @DataSet(value = ["datasets/create-address.yaml"])
    fun `should throw badRequest while trying to update favorite address with wrong request`() {
        Given {
            spec(requestSpecification)
        } When {
            patch("")
        } Then {
            statusCode(HttpStatus.BAD_REQUEST.value())
        }
    }
}

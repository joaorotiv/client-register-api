package com.client.register.resources.address

import com.client.register.configs.Setup
import com.github.database.rider.core.api.dataset.DataSet
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class DeleteAddressResourceTest: Setup() {

    override fun basePath(): String = "/v1/address"

    @Test
    @DataSet(value = ["datasets/create-address.yaml"])
    fun `should successfully delete address by id`() {
        Given {
            spec(requestSpecification)
        } When {
            delete("/1")
        } Then {
            statusCode(HttpStatus.OK.value())
        }
    }

    @Test
    @DataSet(value = ["datasets/create-address.yaml"])
    fun `should throw notFound while trying to delete non-existent address`() {
        Given {
            spec(requestSpecification)
        } When {
            delete("/999")
        } Then {
            statusCode(HttpStatus.NOT_FOUND.value())
        }
    }

    @Test
    fun `should return badRequest while trying to delete address with wrong request`() {
        Given {
            spec(requestSpecification)
        } When {
            delete("")
        } Then {
            statusCode(HttpStatus.BAD_REQUEST.value())
        }
    }
}



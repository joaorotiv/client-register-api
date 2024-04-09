package com.client.register.resources.client

import com.client.register.configs.Setup
import com.github.database.rider.core.api.dataset.DataSet
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class DeleteClientResourceTest: Setup() {

    override fun basePath(): String = "/v1/client"

    @Test
    @DataSet(value = ["datasets/create-client.yaml"])
    fun `should successfully delete client by id`() {
        Given {
            spec(requestSpecification)
        } When {
            delete("/1")
        } Then {
            statusCode(HttpStatus.OK.value())
        }
    }

    @Test
    fun `should throw notFound while trying to delete non-existent client`() {
        Given {
            spec(requestSpecification)
        } When {
            delete("/999")
        } Then {
            statusCode(HttpStatus.NOT_FOUND.value())
        }
    }

    @Test
    fun `should return badRequest while trying to delete client with wrong request`() {
        Given {
            spec(requestSpecification)
        } When {
            delete("")
        } Then {
            statusCode(HttpStatus.BAD_REQUEST.value())
        }
    }
}



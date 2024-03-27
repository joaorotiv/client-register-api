package com.client.register.resources

import com.client.register.configs.Setup
import com.github.database.rider.core.api.dataset.DataSet
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class GetClientResourceTest: Setup() {

    override fun basePath(): String = "/v1/client"

    @Test
    @DataSet(value = ["datasets/create-clients.yaml"])
    fun `should successfully get client by id`() {
        Given {
            spec(requestSpecification)
        } When {
            get("/3")
        } Then {
            statusCode(HttpStatus.OK.value())
        }
    }

    @Test
    @DataSet(value = ["datasets/create-clients.yaml"])
    fun `should successfully get client by name`() {
        Given {
            spec(requestSpecification)
            queryParam("name", "Jorge")
        } When {
            get()
        } Then {
            statusCode(HttpStatus.OK.value())
        }
    }

    @Test
    @DataSet(value = ["datasets/create-clients.yaml"])
    fun `should successfully get filtered clients`() {
        Given {
            spec(requestSpecification)
        } When {
            get("/filtered")
        } Then {
            statusCode(HttpStatus.OK.value())
        }
    }

    @Test
    fun `should throw notFound while trying to get non-existent client`() {
        Given {
            spec(requestSpecification)
        } When {
            get("/999")
        } Then {
            statusCode(HttpStatus.NOT_FOUND.value())
        }
    }
}



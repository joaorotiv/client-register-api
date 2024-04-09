package com.client.register.resources.address

import com.client.register.configs.Setup
import com.github.database.rider.core.api.dataset.DataSet
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class GetAddressResourceTest: Setup() {

    override fun basePath(): String = "/v1/address"

    @Test
    @DataSet(value = ["datasets/create-address.yaml"])
    fun `should successfully get address by id`() {
        Given {
            spec(requestSpecification)
        } When {
            get("/1/3")
        } Then {
            statusCode(HttpStatus.OK.value())
        }
    }

    @Test
    @DataSet(value = ["datasets/create-address.yaml"])
    fun `should successfully get address by zipcode`() {
        Given {
            spec(requestSpecification)
            queryParam("zipcode", "12987-12")
        } When {
            get("/1")
        } Then {
            statusCode(HttpStatus.OK.value())
        }
    }

    @Test
    @DataSet(value = ["datasets/create-address.yaml"])
    fun `should successfully get all addresses from client`() {
        Given {
            spec(requestSpecification)
        } When {
            get("/1/all")
        } Then {
            statusCode(HttpStatus.OK.value())
        }
    }

    @Test
    fun `should throw notFound while trying to get non-existent address`() {
        Given {
            spec(requestSpecification)
        } When {
            get("/1/999")
        } Then {
            statusCode(HttpStatus.NOT_FOUND.value())
        }
    }
}



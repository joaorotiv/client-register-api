package com.client.register.configs

import com.github.database.rider.junit5.api.DBRider
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration

@DBRider
@TestInstance(PER_CLASS)
@ContextConfiguration
@ActiveProfiles(value = ["test"])
@SpringBootTest(webEnvironment = RANDOM_PORT)
abstract class Setup {

    companion object { lateinit var requestSpecification: RequestSpecification }

    abstract fun basePath(): String

    @LocalServerPort
    var port: Int = 0

    @BeforeAll
    fun setup() {
        requestSpecification = RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(port)
            .setBasePath(basePath())
            .setContentType(ContentType.JSON)
            .setRelaxedHTTPSValidation()
            .build()
    }

    @AfterAll
    fun tearDown() {
        RestAssured.reset()
    }
}
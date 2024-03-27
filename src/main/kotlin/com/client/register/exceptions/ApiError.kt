package com.client.register.exceptions

import org.springframework.http.HttpStatus

data class ApiError(
    var message: String?,
    val status: HttpStatus,
    private val code: Int = status.value()
)
package com.client.register.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class ZipcodeNotFoundException(message: String?) : RuntimeException(message)
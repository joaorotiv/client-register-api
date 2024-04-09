package com.client.register.validators

interface Validator<T> {

    fun validate(value: T): Boolean
}
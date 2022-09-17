package com.springproject.exceptions

import org.springframework.http.HttpStatus

data class WebshopException(
    override val message: String,
    val statusCode: HttpStatus
    ): RuntimeException(message){
}
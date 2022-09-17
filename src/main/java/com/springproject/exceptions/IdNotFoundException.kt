package com.springproject.exceptions

import org.springframework.http.HttpStatus

data class IdNotFoundException(
    override val message: String,
    val statusCode: HttpStatus = HttpStatus.BAD_REQUEST
): RuntimeException(message){
}
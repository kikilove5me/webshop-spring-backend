package com.springproject.exceptions

import org.springframework.http.HttpStatus

data class IdNotFoundException(
    override val message: String,
    val statusCode: HttpStatus
): Exception(message){
}
package com.springproject.model

data class OrderPositionCreateRequest(
        val productId: String,
        val quantity: Long
){

}

package com.springproject.model

data class GetOrderPositionResponse(
    val id: String,
    val product: ProductResponse,
    val quantity: Int,

    ) {

}

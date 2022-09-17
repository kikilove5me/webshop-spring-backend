package com.springproject.model

data class ShoppingCardResponse(
    val customerId: String,
    val orderPositions: List<OrderPositionResponse>,
    val totalAmountInCent: Long,
    val deliveryCostInCent: Long,
    val deliveryOption: String
)

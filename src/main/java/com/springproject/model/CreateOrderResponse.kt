package com.springproject.model

import java.time.LocalDateTime

data class OrderResponse (
        val id: String,
        val customerId: String,
        val orderTime: LocalDateTime,
        val status: OrderStatus,
        val orderPosition: List<OrderPositionResponse>
        )

enum class OrderStatus {
    NEW, CONFIRMED, SENT, DELIVERED, CALCELED
}

data class OrderPositionResponse(
        val id: String,
        val orderId: String,
        val productId:String,
        val quantity: Int
)
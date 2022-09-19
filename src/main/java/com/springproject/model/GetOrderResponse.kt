package com.springproject.model

import java.time.LocalDateTime

data class GetOrderResponse(
    val id: String,
    val orderTime: LocalDateTime,
    val status: OrderStatus,
    val customer: CustomerResponse,
    val orderPositions: List<GetOrderPositionResponse>
)
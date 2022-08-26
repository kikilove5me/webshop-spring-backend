package com.springproject.repository

import com.springproject.model.OrderPositionResponse

class OrderPositionRepository {

    val orderPositions = mutableListOf<OrderPositionResponse>()

    fun save(orderPositionResponse: OrderPositionResponse) {
        orderPositions.add(orderPositionResponse);
    }
}

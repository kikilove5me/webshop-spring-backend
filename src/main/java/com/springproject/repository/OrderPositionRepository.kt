package com.springproject.repository

import com.springproject.model.OrderPositionResponse
import org.springframework.stereotype.Service

@Service
class OrderPositionRepository {

    private val orderPositions = mutableListOf<OrderPositionResponse>()

    fun save(orderPositionResponse: OrderPositionResponse) {
        orderPositions.add(orderPositionResponse);
    }

    fun findAllByOrderIds(orderIds: List<String>): List<OrderPositionResponse> {
        return orderPositions.filter { orderIds.contains(it.orderId) }
    }
}

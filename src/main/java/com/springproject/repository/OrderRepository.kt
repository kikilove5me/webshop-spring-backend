package com.springproject.repository

import com.springproject.model.OrderCreateRequest
import com.springproject.model.OrderResponse
import com.springproject.model.OrderStatus
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class OrderRepository {
    private val orders = mutableListOf<OrderResponse>();
    fun save(request: OrderCreateRequest): OrderResponse {
        val orderResponse = OrderResponse(
                id = UUID.randomUUID().toString(),
                customerId = request.customerId,
                orderTime = LocalDateTime.now(),
                status = OrderStatus.NEW,
                orderPosition = emptyList()
        )
        orders.add(orderResponse)
        return orderResponse
    }

    fun findByID(orderId: String): OrderResponse? {
        return orders.find { it.id == orderId }
    }

    fun findAllByCustomerIdWhereOrderStatusIsNew(customerId: String): List<OrderResponse> {
        return orders.filter { it.customerId == customerId && it.status == OrderStatus.NEW}
    }

}
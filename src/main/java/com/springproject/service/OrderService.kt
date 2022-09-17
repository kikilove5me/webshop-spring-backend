package com.springproject.service

import com.springproject.exceptions.IdNotFoundException
import com.springproject.exceptions.WebshopException
import com.springproject.model.OrderCreateRequest
import com.springproject.model.OrderPositionCreateRequest
import com.springproject.model.OrderPositionResponse
import com.springproject.model.OrderResponse
import com.springproject.repository.CustomerRepository
import com.springproject.repository.OrderPositionRepository
import com.springproject.repository.OrderRepository
import com.springproject.repository.ProductRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderService(
        val productRepository: ProductRepository,
        val orderRepository: OrderRepository,
        val customerRepository: CustomerRepository,
        val orderPositionRep : OrderPositionRepository
) {

    fun createOrder(request: OrderCreateRequest): OrderResponse {
        customerRepository.findById(request.customerId)
        return orderRepository.save(request)
    }

    fun createNewPositionForOrder(orderId: String, request: OrderPositionCreateRequest): OrderPositionResponse {

        orderRepository.findByID(orderId)
            ?: throw IdNotFoundException(
                message = "Order with ${orderId} not found",
                statusCode = HttpStatus.BAD_REQUEST
            )

        if (productRepository.findByID(request.productId).isEmpty)
            throw WebshopException(message = "Product ${request.productId} not found", statusCode = HttpStatus.BAD_REQUEST)

        val orderPositionResponse = OrderPositionResponse(
                id = UUID.randomUUID().toString(),
                orderId = orderId,
                productId = request.productId,
                quantity = request.quantity

        )

        orderPositionRep.save(orderPositionResponse)
        return orderPositionResponse
    }
}
package com.springproject.service

import com.springproject.model.OrderCreateRequest
import com.springproject.model.OrderPositionCreateRequest
import com.springproject.model.OrderPositionResponse
import com.springproject.model.OrderResponse
import com.springproject.repository.CustomerRepository
import com.springproject.repository.OrderPositionRepository
import com.springproject.repository.OrderRepository
import com.springproject.repository.ProductRepository
import java.util.*

class OrderService {


    private val orderRepository = OrderRepository()
    private val customerRepository = CustomerRepository()
    private val orderPositionRep = OrderPositionRepository()
    private val productRepository = ProductRepository()

    fun createOrder(request: OrderCreateRequest): OrderResponse {
        if (checkCustomer(request.customerId))
            throw Exception("Customer not found")

        return orderRepository.save(request)
    }

    private fun checkCustomer(id: String): Boolean{
        if (customerRepository.findById(id) != null)
            return true
        return false
    }

    fun createNewPositionForOrder(orderId: String, request: OrderPositionCreateRequest): OrderPositionResponse {
        if (orderRepository.findByID(orderId) == null)
            throw Exception("Order not found")
        if (productRepository.findByID(request.productId).isEmpty)
            throw Exception("Product not found")

        val orderPositionResponse = OrderPositionResponse(
                id = UUID.randomUUID().toString(),
                productId = request.productId,
                quantity = request.quantity

        )

        orderPositionRep.save(orderPositionResponse)
        return orderPositionResponse
    }
}
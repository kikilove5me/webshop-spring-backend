package com.springproject.service

import com.springproject.entity.OrderEntity
import com.springproject.entity.OrderPositionEntity
import com.springproject.exceptions.IdNotFoundException
import com.springproject.exceptions.WebshopException
import com.springproject.model.*
import com.springproject.repository.*
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class OrderService(
        val productRepository: ProductRepository,
        val orderRepository: OrderRepository,
        val customerRepository: CustomerRepository,
        val orderPositionRep : OrderPositionRepository
) {

    fun createOrder(orderRequest: CreateOrderRequest): OrderResponse {
        customerRepository.getReferenceById(orderRequest.customerId)

        val order = OrderEntity(
            id = UUID.randomUUID().toString(),
            customerId = orderRequest.customerId,
            orderTime = LocalDateTime.now(),
            status = OrderStatus.NEW,
        )
        val savedOrder = orderRepository.save(order)
        return mapToResponse(savedOrder)
    }

    fun createNewPositionForOrder(orderId: String, orderPositionRequest: CreateOrderPositionRequest): OrderPositionResponse {

        orderRepository.getReferenceById(orderId)
            ?: throw IdNotFoundException(
                message = "Order with ${orderId} not found",
                statusCode = HttpStatus.BAD_REQUEST
            )

        if (productRepository.findById(orderPositionRequest.productId).isEmpty)
            throw WebshopException(message = "Product ${orderPositionRequest.productId} not found", statusCode = HttpStatus.BAD_REQUEST)

        val orderPosition = OrderPositionEntity(
                id = UUID.randomUUID().toString(),
                orderId = orderId,
                productId = orderPositionRequest.productId,
                quantity = orderPositionRequest.quantity
        )
        val savedOrderPosition = orderPositionRep.save(orderPosition)
        return mapToResponse(savedOrderPosition)
    }

    fun updateOrder(id: String, request: OrderUpdateRequest): OrderResponse{
        val order = orderRepository.getReferenceById(id) ?: throw IdNotFoundException("Order with id $id not found.")
        val updatedOrder = order.copy(status = request.orderStatus ?: order.status)
        val savedOrder = orderRepository.save(updatedOrder)
        return mapToResponse(savedOrder)
    }

    private fun mapToResponse(savedOrder: OrderEntity) = OrderResponse(
        id = savedOrder.id,
        customerId = savedOrder.customerId,
        orderTime = savedOrder.orderTime,
        status = savedOrder.status,
        orderPosition = emptyList()
    )

    fun getOrder(id: String): GetOrderResponse {
        val order = orderRepository.getReferenceById(id)
        val customer = customerRepository.getReferenceById(order.customerId)
        val positions = orderPositionRep
            .findAll()
            .filter { it.orderId == order.id }
            .map {
                val productEntity = productRepository.getReferenceById(it.productId)
                GetOrderPositionResponse(
                id = it.id,
                quantity = it.quantity,
                product = ProductResponse(
                    productEntity.id,
                    productEntity.name,
                    productEntity.description,
                    productEntity.priceInCent,
                    productEntity.tags
                )
            )}
        return GetOrderResponse(
            id = order.id,
            status = order.status,
            orderTime = order.orderTime,
            customer = CustomerResponse(
                id = customer.id,
                firstName= customer.firstName,
                lastName = customer.lastName,
                email = customer.email
            ),
            orderPositions = positions
        )
    }

    companion object {
        fun mapToResponse(savedOrderPosition: OrderPositionEntity) = OrderPositionResponse(
            id = savedOrderPosition.id,
            orderId = savedOrderPosition.orderId,
            productId = savedOrderPosition.productId,
            quantity = savedOrderPosition.quantity
        )
    }
}

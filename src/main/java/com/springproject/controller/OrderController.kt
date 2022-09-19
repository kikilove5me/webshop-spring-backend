package com.springproject.controller

import com.springproject.model.*
import com.springproject.service.OrderService
import org.springframework.web.bind.annotation.*

@RestController
class OrderController(
        val orderService: OrderService
) {

    @PostMapping("/orders")
    fun createOrder(@RequestBody orderRequest: CreateOrderRequest): OrderResponse {
        return orderService.createOrder(orderRequest);
    }

    @PostMapping("/orders/{id}/positions")
    fun createOrderPosition(
            @PathVariable(name = "id") orderId: String,
            @RequestBody orderPositionRequest: CreateOrderPositionRequest
    ) {
        orderService.createNewPositionForOrder(orderId, orderPositionRequest)
    }

    @PutMapping("/orders/{id}")
    fun updateOrder(@PathVariable id: String, @RequestBody request: OrderUpdateRequest) {
        orderService.updateOrder(id, request)
    }

    @GetMapping("/orders/{id}")
    fun getOrder(@PathVariable id: String): GetOrderResponse {
        return orderService.getOrder(id);
    }
}
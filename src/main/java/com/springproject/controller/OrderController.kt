package com.springproject.controller

import com.springproject.exceptions.WebshopException
import com.springproject.model.OrderCreateRequest
import com.springproject.model.OrderPositionCreateRequest
import com.springproject.model.OrderResponse
import com.springproject.model.ProductResponse
import com.springproject.repository.OrderRepository
import com.springproject.repository.ProductRepository
import com.springproject.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController(
        val orderService: OrderService
) {

    @PostMapping("/orders")
    fun createOrder(@RequestBody request: OrderCreateRequest): OrderResponse {
        return orderService.createOrder(request);
    }

    @PostMapping("/orders/{id}/positions")
    fun createOrderPosition(
            @PathVariable(name = "id") orderId: String,
            @RequestBody request: OrderPositionCreateRequest
    ) {
        orderService.createNewPositionForOrder(orderId, request)
    }
}
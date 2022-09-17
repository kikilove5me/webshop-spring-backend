package com.springproject.controller

import com.springproject.model.CustomerResponse
import com.springproject.model.ShoppingCardResponse
import com.springproject.repository.CustomerRepository
import com.springproject.service.ShoppingCardService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController(
        val customerRepository: CustomerRepository,
        val shoppingCardService: ShoppingCardService
){

    @GetMapping("/customers/{id}")
    fun getCustomerById(@PathVariable id: String): CustomerResponse {
        return customerRepository.findById(id)
    }

    @GetMapping("/customers")
    fun getAllCustomers(): List<CustomerResponse> {
        return customerRepository.getAll()
    }

    @GetMapping("/customer/{id}/shoppingcard")
    fun getShoppingCardByCustomerId( @PathVariable id: String) : ShoppingCardResponse {
        return shoppingCardService.getShoppingCardForCustomer(id)
    }
}
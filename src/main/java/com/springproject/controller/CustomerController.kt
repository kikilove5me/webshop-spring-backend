package com.springproject.controller

import com.springproject.model.CustomerResponse
import com.springproject.model.ShoppingCardResponse
import com.springproject.repository.CustomerRepository
import com.springproject.service.ShoppingCartService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController(
        val customerRepository: CustomerRepository,
        val shoppingCartService: ShoppingCartService
){

    @GetMapping("/customers/{id}")
    fun getCustomerById(@PathVariable id: String): CustomerResponse {
        val customer = customerRepository.getReferenceById(id)
        return CustomerResponse(
            id = customer.id,
            firstName = customer.firstName,
            lastName= customer.lastName,
            email = customer.email)
    }

    @GetMapping("/customers")
    fun getAllCustomers(): List<CustomerResponse> {
        return customerRepository.findAll().map { it -> CustomerResponse(it.id, it.firstName, it.lastName, it.email) };
    }

    @GetMapping("/customer/{id}/shoppingcard")
    fun getShoppingCardByCustomerId( @PathVariable id: String) : ShoppingCardResponse {
        return shoppingCartService.getShoppingCardForCustomer(id)
    }
}
package com.springproject.controller

import com.springproject.model.CustomerResponse
import com.springproject.repository.CustomerRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController {
    val customerRepository : CustomerRepository = CustomerRepository()

    @GetMapping("/customers/{id}")
    fun getCustomerById(@PathVariable id: String): ResponseEntity<CustomerResponse> {
        val customer = customerRepository.findById(id);
        return if (customer == null)
            ResponseEntity.notFound().build()
        else
            ResponseEntity.ok(customer)
    }
}
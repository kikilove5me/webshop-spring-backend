package com.springproject.repository

import com.springproject.exceptions.IdNotFoundException
import com.springproject.model.CustomerResponse
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerRepository {
    private val customers = listOf(
            CustomerResponse(UUID.randomUUID().toString(), "pinco", "pallino", "p.pallino@icloud.com"),
            CustomerResponse("1", "dirk", "Mustermann", "d.mustermann@gmail.com")
    )

    // CustomerResponse oder null
    fun findById(id: String): CustomerResponse {
        return customers.find { it.id == id }
            ?: throw IdNotFoundException("Customer with the id $id not found")
    }

    fun getAll(): List<CustomerResponse> {
        return customers
    }


}
package com.springproject.repository

import com.springproject.model.CustomerResponse
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerRepository {
    val customers = listOf(
            CustomerResponse(
                    UUID.randomUUID().toString(),
                    "pinco",
                    "pallino",
                    "p.pallino@icloud.com"
    ))

    // CustomerResponse oder null
    fun findById(id: String): CustomerResponse? {
        val customer: CustomerResponse? = customers.find { it.id == id }
        return customer
    }

}
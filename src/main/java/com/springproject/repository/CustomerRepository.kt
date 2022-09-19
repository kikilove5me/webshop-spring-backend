package com.springproject.repository

import com.springproject.entity.CustomerEntity
import org.springframework.data.jpa.repository.JpaRepository


interface CustomerRepository: JpaRepository<CustomerEntity, String> {

}



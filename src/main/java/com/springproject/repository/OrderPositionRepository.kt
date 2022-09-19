package com.springproject.repository

import com.springproject.entity.OrderPositionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OrderPositionRepository: JpaRepository<OrderPositionEntity, String> {

}


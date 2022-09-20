package com.springproject.entity

import com.springproject.model.OrderStatus
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name="orders")
data class OrderEntity (
    @Id val id: String,
    val customerId: String,
    val orderTime: LocalDateTime,
    @Enumerated(EnumType.STRING)
    val status: OrderStatus,
)
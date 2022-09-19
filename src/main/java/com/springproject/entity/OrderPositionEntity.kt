package com.springproject.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="order_positions")
data class OrderPositionEntity(
    @Id val id: String,
    val orderId: String,
    val productId:String,
    val quantity: Int
)
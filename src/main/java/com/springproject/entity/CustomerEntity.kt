package com.springproject.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="customers")
data class CustomerEntity(
    @Id val id:String,
    val firstName: String,
    val lastName: String,
    val email: String)

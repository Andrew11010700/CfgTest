package com.cfg.testtask.domain.model

data class Product(
    val productId: Long,
    val name: String,
    val description: String,
    val price: Double,
    val discount: Int,
    val availability: Boolean,
    val brand: String,
    val category: String,
    val rating: Double,
)

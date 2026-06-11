package com.cfg.testtask.domain.repository

import com.cfg.testtask.data.local.ProductEntity
import com.cfg.testtask.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun fetchProducts()

    fun loadProducts(): Flow<List<Product>>

   suspend fun getProductById(id: Long): ProductEntity?

    suspend fun saveProduct(product: ProductEntity)

}
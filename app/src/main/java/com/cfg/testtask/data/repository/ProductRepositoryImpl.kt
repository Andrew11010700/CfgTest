package com.cfg.testtask.data.repository

import com.cfg.testtask.data.local.ProductDao
import com.cfg.testtask.data.local.ProductEntity
import com.cfg.testtask.data.remote.ProductApi
import com.cfg.testtask.domain.model.Product
import com.cfg.testtask.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull

class ProductRepositoryImpl(
    private val api: ProductApi,
    private val dao: ProductDao
): ProductRepository {

    override suspend fun fetchProducts() {
        val productsFromApi = api.getProducts()
        dao.insertProducts(productsFromApi.map {
            ProductEntity(
                productId = it.productId,
                name = it.name,
                description = it.description,
                price = it.price,
                unit = it.unit,
                discount = it.discount,
                availability = it.availability,
                image = it.image,
                brand = it.brand,
                category = it.category,
                rating = it.rating
            )
        })
    }

    override fun loadProducts(): Flow<List<Product>> {
        return dao.getAllProducts().map {
            it.map { entity ->
                Product(
                    productId = entity.productId,
                    name = entity.name,
                    description = entity.description,
                    price = entity.price,
                    discount = entity.discount,
                    availability = entity.availability,
                    brand = entity.brand,
                    category = entity.category,
                    rating = entity.rating
                )
            }
        }
    }

    override suspend fun getProductById(id: Long): ProductEntity? {
        return dao.getById(id)
    }

    override suspend fun saveProduct(product: ProductEntity) {
        dao.save(product)
    }

}

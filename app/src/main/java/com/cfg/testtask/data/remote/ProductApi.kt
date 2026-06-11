package com.cfg.testtask.data.remote

import retrofit2.http.GET

interface ProductApi {

    @GET("api/products")
    suspend fun getProducts(): List<ProductDto>

}
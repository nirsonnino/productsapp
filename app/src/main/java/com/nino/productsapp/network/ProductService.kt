package com.nino.productsapp.network

import com.nino.productsapp.network.model.NetworkResponse
import retrofit2.http.GET

interface ProductService {

    @GET("products")
    suspend fun getProducts(): NetworkResponse
}
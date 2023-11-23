package com.nino.productsapp.network

import com.nino.productsapp.network.model.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {

    @GET("products")
    suspend fun getProducts(@Query("limit") limit: Int?): NetworkResponse
}
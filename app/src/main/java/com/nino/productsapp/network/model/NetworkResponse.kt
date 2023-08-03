package com.nino.productsapp.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkResponse(
    @Json(name = "products")
    val products: List<NetworkProductDetails>,
    @Json(name = "total")
    val total: Int? = null,
    @Json(name = "skip")
    val skip: Int? = null,
    @Json(name = "limit")
    val limit: Int? = null
)
package com.nino.productsapp.network.model


import com.nino.productsapp.database.DatabaseProductDetails
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkProductDetails(
    @Json(name = "id")
    val id: Int,
    @Json(name = "title")
    val title: String?,
    @Json(name = "description")
    val description: String,
    @Json(name = "price")
    val price: Int,
    @Json(name = "discountPercentage")
    val discountPercentage: Double,
    @Json(name = "rating")
    val rating: Double,
    @Json(name = "stock")
    val stock: Int,
    @Json(name = "brand")
    val brand: String,
    @Json(name = "category")
    val category: String,
    @Json(name = "thumbnail")
    val thumbnail: String,
    @Json(name = "images")
    val images: List<String>
)

fun List<NetworkProductDetails>.asDatabaseModel(): List<DatabaseProductDetails> {
    return map {
        DatabaseProductDetails(
            id = it.id,
            title = it.title,
            description = it.description,
            price = it.price,
            discountPercentage = it.discountPercentage,
            rating = it.rating,
            stock = it.stock,
            brand = it.brand,
            category = it.category,
            thumbnail = it.thumbnail,
            images = it.images
        )
    }
}
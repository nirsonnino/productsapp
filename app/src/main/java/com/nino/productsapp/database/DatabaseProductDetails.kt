package com.nino.productsapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nino.productsapp.domain.ProductDetails
import com.nino.productsapp.domain.ProductListItem

@Entity
data class DatabaseProductDetails constructor(
    @PrimaryKey
    val id: Int? = null,
    val title: String? = null,
    val description: String? = null,
    val price: Int? = null,
    val discountPercentage: Double? = null,
    val rating: Double? = null,
    val stock: Int? = null,
    val brand: String? = null,
    val category: String? = null,
    val thumbnail: String? = null,
    val images: List<String>? = arrayListOf()
)

fun DatabaseProductDetails.asDetailsDomainModel(): ProductDetails {
    return ProductDetails(
        id = id,
        title = title,
        description = description,
        price = price,
        discountPercentage = discountPercentage,
        rating = rating,
        stock = stock,
        brand = brand,
        category = category,
        thumbnail = thumbnail,
        images = images
    )
}

fun List<DatabaseProductDetails>.asListDomainModel(): List<ProductListItem> {
    return map {
        ProductListItem(
            id = it.id,
            title = it.title,
            thumbnail = it.thumbnail,
            category = it.category,
            price = it.price
        )
    }
}
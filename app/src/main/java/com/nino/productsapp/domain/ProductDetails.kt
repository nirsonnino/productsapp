package com.nino.productsapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductDetails(
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
    val images: List<String>? = listOf()
) : Parcelable {
    fun getPriceDisplay() = "$${String.format("%.2f", price?.toDouble())}"
}
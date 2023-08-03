package com.nino.productsapp.domain

data class ProductListItem(
    val id: Int? = null,
    val title: String? = null,
    val thumbnail: String? = null,
    val category: String? = null,
    val price: Int? = null
) {
    fun getPriceDisplay() = "$${String.format("%.2f", price?.toDouble())}"
}
package com.nino.productsapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.nino.productsapp.database.ProductsDatabase
import com.nino.productsapp.database.asDetailsDomainModel
import com.nino.productsapp.database.asListDomainModel
import com.nino.productsapp.domain.ProductDetails
import com.nino.productsapp.domain.ProductListItem
import com.nino.productsapp.network.ProductService
import com.nino.productsapp.network.model.asDatabaseModel
import timber.log.Timber
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productService: ProductService,
    private val database: ProductsDatabase,
) {

    val products: LiveData<List<ProductListItem>> =
        database.productsDao.getDatabaseProducts().map { it.asListDomainModel() }

    fun getProductDetails(title: String): LiveData<ProductDetails> =
        database.productsDao.getProductDetails(title).map {
            it.asDetailsDomainModel()
        }

    fun getProductsByCategory(category: String) =
        database.productsDao.getProductsByCategory(category).map { it.asListDomainModel() }

    suspend fun refreshProductList() {
        try {
            val products = productService.getProducts().products
            database.productsDao.insertAll(products.asDatabaseModel())
        } catch (e: Exception) {
            Timber.w(e)
        }
    }
}
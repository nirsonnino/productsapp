package com.nino.productsapp.views.productList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nino.productsapp.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val repo: ProductRepository
) : ViewModel() {

    val data = repo.products

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repo.refreshProductList()
        }
    }

    fun getProductsByCategory(category: String) = if (category.lowercase(Locale.ROOT) == "all") repo.products else repo.getProductsByCategory(category)
}
package com.nino.productsapp.views.productDetails

import androidx.databinding.ObservableParcelable
import androidx.lifecycle.ViewModel
import com.nino.productsapp.domain.ProductDetails
import com.nino.productsapp.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val repo: ProductRepository
) : ViewModel() {

    val productDetails = ObservableParcelable(ProductDetails())

    fun getProductDetails(title: String) = repo.getProductDetails(title)
}
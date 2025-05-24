package com.nxxr.shopflow.ui.screen

import androidx.lifecycle.ViewModel
import com.nxxr.shopflow.R
import com.nxxr.shopflow.data.ProductModel
import com.nxxr.shopflow.data.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ShopViewModel : ViewModel() {
    private val repository = Repository()
    private val _products = MutableStateFlow(repository.getProducts())
    val products: StateFlow<List<ProductModel>> = _products

    val _categories = MutableStateFlow(listOf(
        "Cleaners" to R.drawable.categorysample,
        "Toner" to R.drawable.product_image,
        "Serums" to R.drawable.categorysample,
        "Moisturisers" to R.drawable.product_image,
        "Sunscreen" to R.drawable.categorysample
    ))

    val categories = _categories
}

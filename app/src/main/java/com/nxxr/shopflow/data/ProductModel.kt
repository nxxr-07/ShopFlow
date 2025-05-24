package com.nxxr.shopflow.data

data class ProductModel(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: Int,
    val reviews: Int,
    val inStock: Boolean,
)
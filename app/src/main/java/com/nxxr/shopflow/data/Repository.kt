package com.nxxr.shopflow.data

import com.nxxr.shopflow.R


class Repository {

    fun getProducts(): List<ProductModel> {
        return listOf(
            ProductModel(
                id = 1,
                name = "clencera",
                description = "Fresh clay and algae powered cleanser\nSkin Tightness \n • Dry & Dehydrated Skin",
                price = 35.50, // Assuming this is INR or a placeholder value
                inStock = true,
                reviews = 210, // From visual estimation in the image
                imageUrl = R.drawable.product_image // Placeholder image
            ),
            ProductModel(
                id = 2,
                name = "glow",
                description = "Fresh clay and algae powered cleanser\nSkin Tightness \n • Dry & Dehydrated Skin",
                price = 35.50,
                inStock = true,
                reviews = 210,
                imageUrl = R.drawable.product_image
            ),
            ProductModel(
                id = 3,
                name = "afterglow",
                description = "Fresh clay and algae powered cleanser\nSkin Tightness \n • Dry & Dehydrated Skin",
                price = 35.50,
                inStock = false,
                reviews = 210,
                imageUrl = R.drawable.product_image
            )
            // Add more products here if they were visible in the image
        )
    }
}

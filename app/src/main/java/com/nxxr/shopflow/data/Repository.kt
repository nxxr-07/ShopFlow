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
            ),
            ProductModel(
                id = 4,
                name = "pure detox",
                description = "Charcoal-infused mask for deep pore cleansing\n• Detoxifying \n • Oil Control \n • Acne-prone Skin",
                price = 28.00,
                inStock = true,
                reviews = 98,
                imageUrl = R.drawable.product_image // Ensure you have this drawable, or use a default
            ),
            ProductModel(
                id = 5,
                name = "elixir drops",
                description = "Concentrated vitamin C booster for luminosity\n• Evens Tone \n • Boosts Radiance \n • Anti-oxidant",
                price = 75.20,
                inStock = false,
                reviews = 420,
                imageUrl = R.drawable.product_image // Ensure you have this drawable, or use a default
            ),
            ProductModel(
                id = 6,
                name = "comfort balm",
                description = "Soothing recovery balm for irritated skin\n• Reduces Redness \n • Calms Irritation \n • Post-procedure Care",
                price = 39.00,
                inStock = true, // Another out-of-stock example
                reviews = 187,
                imageUrl = R.drawable.product_image // Ensure you have this drawable, or use a default
            )
            // Add more products here if they were visible in the image
        )
    }
}

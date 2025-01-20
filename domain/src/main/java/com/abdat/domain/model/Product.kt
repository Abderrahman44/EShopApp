package com.abdat.domain.model


data class Product(
    val categoryId: Int,
    val image: String,
    val id: Int,
    val description: String,
    val title: String,
    val price: Double,
) {
    val priceString: String
        get() = "$$price"
}

package com.abdat.domain.model


data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
) {
    val priceString: String
        get() = "$$price"
}

package com.abdat.data.dto

import com.abdat.domain.model.Product
import kotlinx.serialization.Serializable

@Serializable
data class ProductModule(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
) {
    fun toProduct() = Product(
        id = id,
        title = title,
        price = price,
        category = category,
        description = description,
        image = image
    )
}
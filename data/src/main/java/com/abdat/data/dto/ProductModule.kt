package com.abdat.data.dto

import com.abdat.domain.model.Product
import kotlinx.serialization.Serializable

@Serializable
data class ProductModule(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val image: String,
    val categoryId: Int,
) {
    fun toProduct() = Product(
        id = id,
        title = title,
        price = price,
        categoryId = categoryId,
        description = description,
        image = image
    )
}
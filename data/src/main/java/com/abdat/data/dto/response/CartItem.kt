package com.abdat.data.dto.response

import android.R.attr.name
import com.abdat.domain.model.CartItemModel
import kotlinx.serialization.Serializable

@Serializable
data class CartItem(
    val id: Int,
    val productId: Int,
    val userId: Int,
    val name: String,
    val price: Double,
    val imageUrl: String?,
    val quantity: Int,
    val productName: String,
) {
    fun toCartItemModel() = CartItemModel(
        id = id,
        productId = productId,
        userId = userId,
        name = name,
        price = price,
        imageUrl = imageUrl,
        quantity = quantity,
        productName = productName
    )
}
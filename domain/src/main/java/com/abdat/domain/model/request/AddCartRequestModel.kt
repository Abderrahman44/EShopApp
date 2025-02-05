package com.abdat.domain.model.request

data class AddCartRequestModel(
    val userId: Int,
    val productId: Int,
    val price: Double,
    val quantity: Int,
    val productName: String,
)

package com.abdat.domain.model.request

import com.abdat.domain.model.CartItemModel

data class CartModel(
    val msg: String,
    val data: List<CartItemModel>,
)
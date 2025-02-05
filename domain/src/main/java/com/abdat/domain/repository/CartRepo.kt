package com.abdat.domain.repository

import com.abdat.domain.model.CartItemModel
import com.abdat.domain.model.request.AddCartRequestModel
import com.abdat.domain.model.request.CartModel
import com.abdat.domain.remote.ResultWrapper

interface CartRepo {
    suspend fun addProductToCart(request: AddCartRequestModel): ResultWrapper<CartModel>
}
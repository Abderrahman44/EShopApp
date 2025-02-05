package com.abdat.domain.remote

import com.abdat.domain.model.CartItemModel
import com.abdat.domain.model.CategoriesListModel
import com.abdat.domain.model.ProductListModel
import com.abdat.domain.model.request.AddCartRequestModel
import com.abdat.domain.model.request.CartModel

interface NetworkService {
    suspend fun getProducts(category: Int): ResultWrapper<ProductListModel>
    suspend fun getCategories(): ResultWrapper<CategoriesListModel>
    suspend fun addProductToCart(request: AddCartRequestModel): ResultWrapper<CartModel>
}

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value : T) : ResultWrapper<T>()
    data class  Error(val message : Exception) : ResultWrapper<Nothing>()
}

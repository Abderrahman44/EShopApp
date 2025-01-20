package com.abdat.domain.remote

import com.abdat.domain.model.ProductListModel

interface NetworkService {
    suspend fun getProducts(category: Int): ResultWrapper<ProductListModel>
    suspend fun getCategories(): ResultWrapper<List<String>>
}

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value : T) : ResultWrapper<T>()
    data class  Error(val message : Exception) : ResultWrapper<Nothing>()
}

package com.abdat.data.repository

import com.abdat.domain.model.request.AddCartRequestModel
import com.abdat.domain.remote.NetworkService
import com.abdat.domain.repository.CartRepo

class CartRepoImpl(val networkService: NetworkService): CartRepo {
    override suspend fun addProductToCart(request: AddCartRequestModel) =
        networkService.addProductToCart(request)


}
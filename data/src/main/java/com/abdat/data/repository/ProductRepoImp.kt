package com.abdat.data.repository

import com.abdat.domain.remote.NetworkService
import com.abdat.domain.repository.ProductRepo

class ProductRepoImp(private val networkService: NetworkService ): ProductRepo {
    override suspend fun getProducts(category: String)
    = networkService.getProducts(category)
}
package com.abdat.domain.repository

import com.abdat.domain.model.Product
import com.abdat.domain.remote.ResultWrapper

interface ProductRepo {
    suspend fun getProducts(): ResultWrapper<List<Product>>
}
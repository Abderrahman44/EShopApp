package com.abdat.domain.remote

import com.abdat.domain.model.Product

interface NetworkService {
    suspend fun getPosts(): List<Product>

}
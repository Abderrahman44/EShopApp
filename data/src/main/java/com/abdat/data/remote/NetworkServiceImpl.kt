package com.abdat.data.remote

import com.abdat.data.dto.ProductModule
import com.abdat.domain.model.Product
import com.abdat.domain.remote.NetworkService
import com.abdat.domain.remote.ResultWrapper
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.request.*

class NetworkServiceImpl(private val client:HttpClient) : NetworkService {
    override suspend fun getProducts(category: String): ResultWrapper<List<Product>> {
        return try {
            val productModel: List<ProductModule> = client.get(category).body()
            var product = productModel.map { it.toProduct() }
            ResultWrapper.Success(product)
        } catch (e: Exception) {
            println("Error: ${e.message}")
            ResultWrapper.Error(e)
        }
    }
}




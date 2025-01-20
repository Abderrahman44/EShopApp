package com.abdat.data.remote

import com.abdat.data.dto.HttpRoutes
import com.abdat.data.dto.ProductModule
import com.abdat.data.dto.response.ProductListResponse
import com.abdat.domain.model.ProductListModel
import com.abdat.domain.remote.NetworkService
import com.abdat.domain.remote.ResultWrapper
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.request.*

class NetworkServiceImpl(private val client: HttpClient) : NetworkService {
    override suspend fun getProducts(category: Int): ResultWrapper<ProductListModel> {
        return try {
            val productModel: ProductListResponse =
                client.get("${HttpRoutes.GET_CATEGORY}${category}").body()
            var product = productModel.toProductList()
            ResultWrapper.Success(product)
        } catch (e: Exception) {
            println("Error: ${e.message}")
            ResultWrapper.Error(e)
        }
    }
    override suspend fun getCategories(): ResultWrapper<List<String>> {
        return try {
            val category: List<String> =
                client.get("${HttpRoutes.GET_PRODUCTS}${HttpRoutes.GET_CATEGORIES}").body()
            ResultWrapper.Success(category)
        } catch (e: Exception) {
            println("Error: ${e.message}")
            ResultWrapper.Error(e)
        }
    }
}




package com.abdat.data.remote

import com.abdat.data.dto.ProductModule
import com.abdat.domain.model.Product
import com.abdat.domain.remote.NetworkService
import com.abdat.domain.remote.ResultWrapper
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json


class NetworkServiceImpl(private val client:HttpClient) : NetworkService {
    override suspend fun getProducts(): ResultWrapper<List<Product>> {
//        val client: HttpClient = HttpClient(OkHttp) {
//            defaultRequest { url("https://fakestoreapi.com/") }
//            install(Logging) {
//                logger = Logger.SIMPLE
//            }
//            install(HttpTimeout) {
//                requestTimeoutMillis = 15000 // Set a 10-second timeout
//                connectTimeoutMillis = 15000
//                socketTimeoutMillis = 15000
//            }
//            install(ContentNegotiation) {
//                json(Json {
//                    ignoreUnknownKeys = true
//                })
//            }
//        }
        return try {
            val productModel: List<ProductModule> = client.get("/products").body()
            var product = productModel.map { it.toProduct() }
            ResultWrapper.Success(product)
        } catch (e: Exception) {
            // 3xx - responses
            println("Error: ${e.message}")
            ResultWrapper.Error(e)
        }
    }
}

//show data
fun main() = runBlocking {
    val networkService =
        NetworkServiceImpl(HttpClient()) // Assuming NetworkServiceImpl is your network service class
    val requestResult = networkService.getProducts()

   when (requestResult) {
    is ResultWrapper.Success -> {
        val products = requestResult.value
        products.forEach { product ->
            println("Title: ${product.title}")
            println("Description: ${product.description}")
            println("Price: $${product.price}")
            println("--------------------")
        }
    }
    else -> {
        println("Error: $requestResult")
    }
}
}



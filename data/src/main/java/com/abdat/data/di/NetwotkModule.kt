package com.abdat.data.di

import android.util.Log
import com.abdat.data.dto.HttpRoutes
import com.abdat.data.remote.NetworkServiceImpl
import com.abdat.domain.model.request.AddCartRequestModel
import com.abdat.domain.remote.NetworkService
import com.abdat.domain.remote.ResultWrapper
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient(OkHttp) {
            defaultRequest {
                url(HttpRoutes.BASE_URL)
                header("Content-Type", "application/json")
            }

            install(Logging) {
                logger = Logger.SIMPLE
            }
            /*          logging
                install(Logging) {
                    level = LogLevel.ALL
                    logger = object : Logger {
                        override fun log(message: String) {
                            Log.d("BackEndHandler", message)
                        }
                    }
                }*/
            install(HttpTimeout) {
                requestTimeoutMillis = 10000
                connectTimeoutMillis = 15000
                socketTimeoutMillis = 15000
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true

                    //coerceInputValues = true

                })
            }
        }
    }
    single<NetworkService> {
        NetworkServiceImpl(get())
    }
}


val client: HttpClient = HttpClient(OkHttp) {
    defaultRequest {
        url(HttpRoutes.BASE_URL)
        header("Content-Type", "application/json")

    }

//    install(Logging) {
//        logger = Logger.SIMPLE
//    }
    install(Logging) {
        level = LogLevel.NONE
        logger = object : Logger {
            override fun log(message: String) {
                Log.d("BackEndHandler", message)
            }
        }
    }
    install(HttpTimeout) {
        requestTimeoutMillis = 10000
        connectTimeoutMillis = 15000
        socketTimeoutMillis = 15000
    }
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
        })
    }
}


fun main() = runBlocking {
    val networkService =
        NetworkServiceImpl(client) // Assuming NetworkServiceImpl is your network service class
    //val requestResult = networkService.getProducts(HttpRoutes.GET_ELECTRONIC_PRODUCTS)
    /*    val addonCart: AddCartRequestModel = AddCartRequestModel(
            1,2,999.0,1,"iPhone 14 Pro"
        )
        val requestCategoryResult = networkService.addProductToCart(addonCart)
        when (requestCategoryResult) {
            is ResultWrapper.Error -> {}
            is ResultWrapper.Success -> {
                val categories = requestCategoryResult.value.data
                val msg = requestCategoryResult.value.msg

                categories.forEach { product ->
                    println("Product: ${product.name}")
                    println("Price: $${product.price}")
                    println("quantity: ${product.quantity}")
                    println("--------------")
                    println("Message: $msg")
                }
            }
        }
        */
    /*   when (requestResult)
     {
           is ResultWrapper.Success -> {
               val products = requestResult.value
               products.forEach { product ->
                   println("Title: ${product.title}")
                   println("Description: ${product.description}")
                   println("Price: $${product.price}")
                   println("Category: ${product.category}")
                   println("Image URL: ${product.image}")
                   println("--------------------")
               }
           }
           else -> {
               println("Error: $requestResult")
           }
       }*/
    val categoryResult = networkService.getCategories()
    when (categoryResult) {
        is ResultWrapper.Success -> {
            val categories = categoryResult.value.categories
            categories.forEach { category ->
                println("Category: ${category.title}")
            }
        }

        is ResultWrapper.Error -> {
            println("Error: ${categoryResult.message}")
        }
    }
    client.close()
}



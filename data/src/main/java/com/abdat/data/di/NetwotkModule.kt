package com.abdat.data.di

import com.abdat.data.remote.NetworkServiceImpl
import com.abdat.domain.remote.NetworkService
import com.abdat.domain.remote.ResultWrapper
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
         HttpClient(OkHttp) {
            defaultRequest { url("https://fakestoreapi.com/") }
            install(Logging) {
                logger = Logger.SIMPLE
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 15000 // Set a 10-second timeout
                connectTimeoutMillis = 15000
                socketTimeoutMillis = 15000
            }
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }
    single<NetworkService> {
        NetworkServiceImpl(get())
    }
}


val client: HttpClient = HttpClient(OkHttp) {
    defaultRequest { url("https://fakestoreapi.com/") }
    install(Logging) {
        logger = Logger.SIMPLE
    }
    install(HttpTimeout) {
        requestTimeoutMillis = 15000 // Set a 10-second timeout
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


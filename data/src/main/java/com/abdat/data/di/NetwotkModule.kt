package com.abdat.data.di

import com.abdat.data.remote.NetworkServiceImpl
import com.abdat.domain.remote.NetworkService
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel.*
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
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
    }
    single<NetworkService> {
        NetworkServiceImpl()

    }
}
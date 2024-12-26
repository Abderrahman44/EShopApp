package com.abdat.data.remote

import com.abdat.data.dto.ProductModule
import com.abdat.domain.model.Product
import com.abdat.domain.remote.NetworkService
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp

import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class NetworkServiceImpl(): NetworkService {
    private val client: HttpClient = HttpClient(OkHttp){
        defaultRequest { url("https://fakestoreapi.com/") }
        install(Logging) {
            logger = Logger.SIMPLE
        }
        install(ContentNegotiation){
            json(Json{
                ignoreUnknownKeys = true
            })
        }
    }
    override suspend fun getPosts(): List<Product> {
        return try {
           val products : List<ProductModule> = client.get("/products").body()
            products.map { it.toProduct() }
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            emptyList<Product>()
        }
    }




}




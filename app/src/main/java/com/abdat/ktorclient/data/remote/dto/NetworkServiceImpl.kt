package com.abdat.ktorclient.data.remote.dto

import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.engine.okhttp.OkHttp
//import io.ktor.client.features.*
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

import kotlinx.serialization.serializer

class NetworkServiceImpl(): NetworkService {
    private val client: HttpClient = HttpClient(OkHttp){
        defaultRequest { url("https://jsonplaceholder.typicode.com/") }
        install(Logging) {
            logger = Logger.SIMPLE
        }
        install(ContentNegotiation){
            json(Json{
                ignoreUnknownKeys = true
            })
        }
    }
    override suspend fun getPosts(): List<PostResponse> {
        return try {
            client.get("/posts").body()
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            emptyList<PostResponse>()
        }
    }

    override suspend fun createPost(postRequest: PostRequest): PostResponse {
        TODO("Not yet implemented")
    }


}




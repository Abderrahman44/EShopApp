package com.abdat.ktorclient.data.remote.dto

interface NetworkService {
    suspend fun getPosts(): List<PostResponse>
    suspend fun createPost(postRequest: PostRequest): PostResponse

}
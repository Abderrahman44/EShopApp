package com.abdat.ktorclient.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostRequest(
    val userId: Int,
    val title: String,
    val body: String
)

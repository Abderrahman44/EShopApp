package com.abdat.domain.repository

import com.abdat.domain.remote.ResultWrapper

interface CategoryRepo {
    suspend fun getCategories(): ResultWrapper<List<String>>
}
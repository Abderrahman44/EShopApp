package com.abdat.data.repository

import com.abdat.domain.remote.NetworkService
import com.abdat.domain.remote.ResultWrapper
import com.abdat.domain.repository.CategoryRepo

class CategoryRepoImpl(val networkService: NetworkService): CategoryRepo {
    override suspend fun getCategories(): ResultWrapper<List<String>> {
        return networkService.getCategories()
    }
}
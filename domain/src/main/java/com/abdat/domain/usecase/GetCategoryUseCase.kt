package com.abdat.domain.usecase

import com.abdat.domain.remote.ResultWrapper
import com.abdat.domain.repository.CategoryRepo

class GetCategoryUseCase(private val repository: CategoryRepo) {
    suspend operator fun invoke()  = repository.getCategories()
}
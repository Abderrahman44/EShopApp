package com.abdat.domain.usecase

import com.abdat.domain.repository.ProductRepo

class GetProductUseCase(private val repository: ProductRepo) {
    suspend operator fun invoke() = repository.getProducts()

}
package com.abdat.domain.usecase

import com.abdat.domain.model.request.AddCartRequestModel
import com.abdat.domain.repository.CartRepo

class AddToCartUseCase(private val repository: CartRepo) {
    suspend operator fun invoke(request: AddCartRequestModel)
    = repository.addProductToCart(request)
}
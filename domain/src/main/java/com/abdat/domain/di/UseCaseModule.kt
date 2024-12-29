package com.abdat.domain.di

import com.abdat.domain.usecase.GetProductUseCase
import org.koin.dsl.module

val useCaseModule = module{
    //create new instance of GetProductUseCase every time
    factory{ GetProductUseCase(get()) }
}
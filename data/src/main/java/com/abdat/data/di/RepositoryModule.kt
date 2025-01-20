package com.abdat.data.di

import com.abdat.data.repository.CategoryRepoImpl
import com.abdat.data.repository.ProductRepoImp
import com.abdat.domain.repository.CategoryRepo
import com.abdat.domain.repository.ProductRepo
import org.koin.dsl.module

val repositoryModule = module {
    single<ProductRepo> { ProductRepoImp(get()) }
    single<CategoryRepo> { CategoryRepoImpl(get()) }
}

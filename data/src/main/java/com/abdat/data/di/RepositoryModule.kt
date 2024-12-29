package com.abdat.data.di

import com.abdat.data.repository.ProductRepoImp
import com.abdat.domain.repository.ProductRepo
import org.koin.dsl.module

val repositoryModule = module {
    single<ProductRepo> { ProductRepoImp(get()) }
}

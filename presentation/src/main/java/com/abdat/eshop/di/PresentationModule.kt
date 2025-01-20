package com.abdat.eshop.di

import org.koin.dsl.module

val presentationModule = module{
    includes(viewModelModule)
}
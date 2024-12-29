package com.abdat.Eshop.di

import org.koin.dsl.module

val presentationModule = module{
    includes(viewModelModule)
}
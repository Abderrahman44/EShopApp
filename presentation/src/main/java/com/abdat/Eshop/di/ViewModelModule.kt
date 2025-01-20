package com.abdat.Eshop.di

import com.abdat.Eshop.ui.feature.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module{

    viewModel { HomeViewModel(get(),get()) }
}
package com.abdat.eshop.di

import com.abdat.eshop.ui.feature.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module{

    viewModel { HomeViewModel(get(),get()) }
}
package com.abdat.eshop.di

import com.abdat.eshop.ui.feature.product_details.ProductDetailsVM
import org.koin.dsl.module

val presentationModule = module{
    includes(viewModelModule)


}
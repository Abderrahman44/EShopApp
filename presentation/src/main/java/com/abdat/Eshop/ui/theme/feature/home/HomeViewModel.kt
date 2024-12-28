package com.abdat.Eshop.ui.theme.feature.home

import com.abdat.domain.model.Product

class HomeViewModel {

}


sealed class HomeScreenUIEvents {

     object Loading : HomeScreenUIEvents()
    data class Success(val data: List<Product>) : HomeScreenUIEvents()
    data class Error(val message: String) : HomeScreenUIEvents()
}
package com.abdat.Eshop.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdat.data.dto.HttpRoutes
import com.abdat.domain.model.Product
import com.abdat.domain.remote.ResultWrapper
import com.abdat.domain.usecase.GetProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val getProductUseCase: GetProductUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeScreenUIEvents>(HomeScreenUIEvents.Loading)
    val uiState = _uiState.asStateFlow()


    init {
        getAllProducts()
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            _uiState.value = HomeScreenUIEvents.Loading
            val featured = getProducts(HttpRoutes.GET_ELECTRONIC_PRODUCTS)
            val popularProducts = getProducts(HttpRoutes.GET_JEWELERY_PRODUCTS)
            if (featured.isEmpty() || popularProducts.isEmpty()) {
                _uiState.value = HomeScreenUIEvents.Error("Failed to load products")
                return@launch
            }
            _uiState.value = HomeScreenUIEvents.Success(featured, popularProducts)
        }
    }
    private suspend fun getProducts(category: String): List<Product> {
        _uiState.value = HomeScreenUIEvents.Loading
        // Call the use case to get the products
        getProductUseCase(category).let { result ->
            return when (result) {
                is ResultWrapper.Success -> {
                    result.value
                }

                is ResultWrapper.Error -> {
                    emptyList()
                }
            }
        }
    }

}


sealed class HomeScreenUIEvents {

    object Loading : HomeScreenUIEvents()
    data class Success(
        val featured: List<Product>,
        val popularProducts: List<Product>,
    ) :
        HomeScreenUIEvents()

    data class Error(val message: String) : HomeScreenUIEvents()
}
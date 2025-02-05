package com.abdat.eshop.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdat.data.dto.HttpRoutes
import com.abdat.domain.model.CategoriesListModel
import com.abdat.domain.model.Category
import com.abdat.domain.model.Product
import com.abdat.domain.remote.ResultWrapper
import com.abdat.domain.usecase.GetCategoryUseCase
import com.abdat.domain.usecase.GetProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getProductUseCase: GetProductUseCase,
    private val getCategoryUseCase: GetCategoryUseCase
    ) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeScreenUIEvents>(HomeScreenUIEvents.Loading)
    val uiState = _uiState.asStateFlow()


    init {
        getAllProducts()
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            _uiState.value = HomeScreenUIEvents.Loading
            val featured = getProducts(1)
            val popularProducts = getProducts(2)
            val categories = getCategory()
            if (featured.isEmpty() && popularProducts.isEmpty() && categories.isEmpty()) {
                _uiState.value = HomeScreenUIEvents.Error("Failed to load products")
                return@launch
            }
            _uiState.value = HomeScreenUIEvents.Success(featured, popularProducts,categories )
        }
    }
    private suspend fun getCategory(): List<String> {
        getCategoryUseCase().let { result ->
            return when (result) {
                is ResultWrapper.Success -> {
                    return (result).value.categories.map { it.title }
                }

                is ResultWrapper.Error -> {
                    return emptyList()
                }
            }
        }
    }

    private suspend fun getProducts(category: Int): List<Product> {
        _uiState.value = HomeScreenUIEvents.Loading
        // Call the use case to get the products
        getProductUseCase(category).let { result ->
            return when (result) {
                is ResultWrapper.Success -> {
                    result.value.products
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
        val categories : List<String>
    ) :
        HomeScreenUIEvents()

    data class Error(val message: String) : HomeScreenUIEvents()
}
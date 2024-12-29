package com.abdat.Eshop.ui.theme.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdat.domain.model.Product
import com.abdat.domain.remote.ResultWrapper
import com.abdat.domain.usecase.GetProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val getProductUseCase: GetProductUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeScreenUIEvents>(HomeScreenUIEvents.Loading)
    val uiState= _uiState.asStateFlow()



    init {  // Call the getProducts function when the ViewModel is created
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            _uiState.value = HomeScreenUIEvents.Loading
            // Call the use case to get the products
            getProductUseCase().let { result ->
                when (result) {
                    is ResultWrapper.Success -> {
                         _uiState.value = HomeScreenUIEvents.Success(result.value)
                    }
                    is ResultWrapper.Error -> {
                        val error = result.message.message ?: "An error occurred"
                        _uiState.value = HomeScreenUIEvents.Error(error)
                    }
                }
            }
        }
    }

}


sealed class HomeScreenUIEvents {

         object Loading : HomeScreenUIEvents()
    data class Success(val data: List<Product>) : HomeScreenUIEvents()
    data class Error(val message: String) : HomeScreenUIEvents()
}
package com.abdat.Eshop


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.runtime.remember
import com.abdat.data.remote.NetworkServiceImpl
import com.abdat.domain.model.Product
import com.abdat.Eshop.ui.theme.KtorClientTheme
import com.abdat.Eshop.ui.theme.feature.home.HomeScreen
import com.abdat.Eshop.ui.theme.feature.home.HomeScreenUIEvents
import com.abdat.domain.remote.ResultWrapper
import kotlinx.coroutines.flow.MutableStateFlow


class MainActivity : ComponentActivity() {
//    private val ktorClient = NetworkServiceImpl()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            // A surface container using the 'background' color from the theme
//            val post = remember {
//                MutableStateFlow<List<Product>>(emptyList())
//            }
//            val uiState = remember {
//                MutableStateFlow<HomeScreenUIEvents>(HomeScreenUIEvents.Loading)
//            }
//            LaunchedEffect(key1 = Unit, block = {
//                val result = ktorClient.getProducts()
//                when (result) {
//                    is ResultWrapper.Success -> {
//                        post.value = result.value
//                        uiState.value = HomeScreenUIEvents.Success(post.value)
//                    }
//
//                    is ResultWrapper.Error -> {
//                        val error = (result).message
//                        uiState.value = HomeScreenUIEvents.Error(error.toString())
//                    }
//
//                }
//
//            })
            KtorClientTheme {
                HomeScreen()
            }
        }
    }
}


package com.abdat.eshop.navigation

import com.abdat.eshop.model.UiProductModel
import kotlinx.serialization.SerialInfo
import kotlinx.serialization.Serializable


@Serializable
object HomeScreen

@Serializable
object ProfileScreen

@Serializable
object CartScreen

@Serializable
data class ProductDetailScreen(val product : UiProductModel)


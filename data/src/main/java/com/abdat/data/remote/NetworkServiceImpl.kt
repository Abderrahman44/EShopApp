package com.abdat.data.remote

import com.abdat.data.dto.HttpRoutes
import com.abdat.data.dto.ProductModule
import com.abdat.data.dto.request.AddToCartRequest
import com.abdat.data.dto.response.CartResponse
import com.abdat.data.dto.response.CategoriesListResponse
import com.abdat.data.dto.response.ProductListResponse
import com.abdat.domain.model.CartItemModel
import com.abdat.domain.model.CategoriesListModel
import com.abdat.domain.model.ProductListModel
import com.abdat.domain.model.request.AddCartRequestModel
import com.abdat.domain.model.request.CartModel
import com.abdat.domain.remote.NetworkService
import com.abdat.domain.remote.ResultWrapper
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.request.*

class NetworkServiceImpl(private val client: HttpClient) : NetworkService {
    override suspend fun getProducts(category: Int): ResultWrapper<ProductListModel> {
        return try {
            val productModel: ProductListResponse =
                client.get("${HttpRoutes.GET_CATEGORY}${category}").body()
            var product = productModel.toProductList()
            ResultWrapper.Success(product)
        } catch (e: Exception) {
            println("Error: ${e.message}")
            ResultWrapper.Error(e)
        }
        client.close()
    }

    override suspend fun getCategories(): ResultWrapper<CategoriesListModel> {
        return try {
            val category: CategoriesListResponse=
                client.get(HttpRoutes.GET_CATEGORIES).body()
            ResultWrapper.Success(category.toCategoriesList())
        } catch (e: Exception) {
            println("Error: ${e.message}")
            ResultWrapper.Error(e)
        }
        client.close()
    }

    override suspend fun addProductToCart(request: AddCartRequestModel): ResultWrapper<CartModel> {
        return try {
            val remoteRequest = AddToCartRequest.toAddToCartRequest(request)
            val cartModel: CartResponse =
                client.post("${HttpRoutes.CART}/1") {
                    setBody(remoteRequest)
                }.body()
            ResultWrapper.Success(cartModel.toCartModel())
        } catch (e: Exception) {
            println("Error: ${e.message}")
            ResultWrapper.Error(e)
        }
        client.close()
    }
}




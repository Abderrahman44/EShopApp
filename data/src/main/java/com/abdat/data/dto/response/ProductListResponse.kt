package com.abdat.data.dto.response

import com.abdat.data.dto.ProductModule
import com.abdat.domain.model.ProductListModel
import kotlinx.serialization.Serializable

@Serializable
data class ProductListResponse(
    val data: List<ProductModule>,
    val msg: String
) {
    fun toProductList() = ProductListModel(
        products = data.map { it.toProduct() },
        msg = msg
    )
}
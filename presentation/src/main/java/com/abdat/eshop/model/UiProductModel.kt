package com.abdat.eshop.model

import android.os.Parcelable
import com.abdat.domain.model.Product
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class UiProductModel(
    val categoryId: Int,
    val image: String,
    val id: Int,
    val description: String,
    val title: String,
    val price: Double,
): Parcelable {
    companion object {
        fun fromProduct(product: Product
        ) = UiProductModel(
            categoryId = product.categoryId,
            image = product.image,
            id = product.id,
            description = product.description,
            title = product.title,
            price = product.price
        )
    }
}

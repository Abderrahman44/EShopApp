package com.abdat.data.dto.response

import com.abdat.domain.model.CategoriesListModel
import kotlinx.serialization.Serializable


@Serializable
data class CategoriesListResponse(
    val data: List<CategoryDataModel>,
    val msg: String
) {
    fun toCategoriesList() = CategoriesListModel(
        categories = data.map { it.toCategory() },
        msg = msg
    )
}

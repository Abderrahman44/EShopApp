package com.abdat.domain.repository

import com.abdat.domain.model.CategoriesListModel
import com.abdat.domain.remote.ResultWrapper

interface CategoryRepo {
    suspend fun getCategories(): ResultWrapper<CategoriesListModel>
}
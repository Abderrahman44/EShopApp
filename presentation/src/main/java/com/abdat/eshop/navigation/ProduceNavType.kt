package com.abdat.eshop.navigation

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import com.abdat.eshop.model.UiProductModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
object CustomNavType {

    val ProductType = object : NavType<UiProductModel>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: Bundle, key: String): UiProductModel? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): UiProductModel {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: UiProductModel): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: UiProductModel) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}
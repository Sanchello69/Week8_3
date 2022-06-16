package com.vas.feature_favorite_screen.data.network.api

import android.util.Log
import com.vas.core.utils.Constants
import com.vas.core.utils.Result
import com.vas.feature_favorite_screen.data.network.model.FavoriteCatModelApi
import com.vas.feature_favorite_screen.data.network.model.ImageCatModelApi
import io.ktor.client.*
import io.ktor.client.request.*

class ApiFavorite (private val httpClient: HttpClient) {

    suspend fun getFavoriteResult(): Result<List<ImageCatModelApi>> {
        try {
            val response: List<FavoriteCatModelApi> = httpClient.get(Constants.BASE_URL + Constants.FAVORITE_URL){
                header("x-api-key", Constants.API_KEY)
            }

            return Result(
                status = Result.Status.SUCCESS,
                message = "ok",
                data = response.map { it.image }
            )
        } catch (e: Exception){
            Log.d("error_get_main", "${e.message}")
            return Result(
                status = Result.Status.ERROR,
                message = e.message,
                data = null
            )
        }
    }
}
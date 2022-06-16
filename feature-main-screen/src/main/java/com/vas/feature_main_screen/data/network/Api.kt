package com.vas.feature_main_screen.data.network

import android.util.Log
import com.vas.core.utils.Constants.API_KEY
import com.vas.core.utils.Constants.BASE_URL
import com.vas.core.utils.Constants.CAT_URL
import com.vas.core.utils.Constants.FAVORITE_URL
import com.vas.feature_main_screen.data.model.CatModelApi
import com.vas.feature_main_screen.data.model.FavoriteModelApi
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class Api (private val httpClient: HttpClient) {

    suspend fun getCatsResult(page: Int): List<CatModelApi> {
        try {
            val response: List<CatModelApi> = httpClient.get(BASE_URL + CAT_URL){
                url {
                    parameters.append("limit", "10")
                    parameters.append("page", "$page")
                    parameters.append("order", "rand")
                }
                header("x-api-key", API_KEY)
            }

            return response
        } catch (e: Exception){
            Log.d("error_get_main", "${e.message}")
            return emptyList()
        }
    }

    suspend fun postLike(id: String): String {
        try {
            val response: HttpResponse  = httpClient.post(BASE_URL + FAVORITE_URL){
                header("x-api-key", API_KEY)
                body = FavoriteModelApi(imageId = id, userId = "User-69")
            }
            Log.d("status_api", response.status.value.toString())
            return "SUCCESS"
        } catch (e: Exception){
            return "${e.message}"
        }
    }
}
package com.vas.feature_favorite_screen.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.vas.core.utils.Result
import com.vas.core.utils.resultLiveData
import com.vas.feature_favorite_screen.data.local.dao.FavoriteDao
import com.vas.feature_favorite_screen.data.local.entity.FavoriteModelLocal
import com.vas.feature_favorite_screen.data.network.api.ApiFavorite
import com.vas.feature_favorite_screen.domain.model.FavoriteCatModel
import com.vas.feature_favorite_screen.domain.repository.FavoriteRepository

class FavoriteRepositoryImpl(
    private val api: ApiFavorite, private val dao: FavoriteDao): FavoriteRepository {

    private val resultFavorite = resultLiveData(
        databaseQuery = {dao.getFavorite()},
        networkCall = {api.getFavoriteResult()},
        saveCallResult = {dao.insertAllFavorite(it.map { FavoriteModelLocal(it.imageId) })}
    )

    override  fun getFavoriteResult() : LiveData<Result<List<FavoriteCatModel>>> {
        return resultFavorite.map {
            Result(
                status = it.status,
                message = it.message,
                data = it.data?.map { FavoriteCatModel(it.imageId) }
            )
        }
    }
}
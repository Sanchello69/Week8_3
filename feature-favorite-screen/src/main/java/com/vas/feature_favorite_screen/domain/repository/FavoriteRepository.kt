package com.vas.feature_favorite_screen.domain.repository

import androidx.lifecycle.LiveData
import com.vas.core.utils.Result
import com.vas.feature_favorite_screen.domain.model.FavoriteCatModel

interface FavoriteRepository {
    fun getFavoriteResult() : LiveData<Result<List<FavoriteCatModel>>>
}
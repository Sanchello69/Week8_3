package com.vas.feature_favorite_screen.domain.useCase

import androidx.lifecycle.LiveData
import com.vas.core.utils.Result
import com.vas.feature_favorite_screen.domain.model.FavoriteCatModel
import com.vas.feature_favorite_screen.domain.repository.FavoriteRepository

class GetFavoriteUseCase(private val favoriteRepository: FavoriteRepository) {

    fun execute(): LiveData<Result<List<FavoriteCatModel>>> {
        return favoriteRepository.getFavoriteResult()
    }
}
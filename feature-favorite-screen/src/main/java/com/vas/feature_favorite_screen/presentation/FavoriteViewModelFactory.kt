package com.vas.feature_favorite_screen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vas.feature_favorite_screen.domain.useCase.GetFavoriteUseCase

class FavoriteViewModelFactory(val getFavoriteUseCase: GetFavoriteUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(
                getFavoriteUseCase = getFavoriteUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
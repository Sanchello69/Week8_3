package com.vas.feature_favorite_screen.presentation

import androidx.lifecycle.ViewModel
import com.vas.feature_favorite_screen.domain.useCase.GetFavoriteUseCase

class FavoriteViewModel(private val getFavoriteUseCase: GetFavoriteUseCase): ViewModel() {

    val favorites = getFavoriteUseCase.execute()

}
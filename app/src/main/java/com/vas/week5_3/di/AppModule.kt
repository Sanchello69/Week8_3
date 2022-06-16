package com.vas.week5_3.di

import android.content.Context
import com.vas.feature_favorite_screen.domain.useCase.GetFavoriteUseCase
import com.vas.feature_favorite_screen.presentation.FavoriteViewModelFactory
import com.vas.feature_main_screen.domain.useCase.GetCatsUseCase
import com.vas.feature_main_screen.domain.useCase.PostLikeUseCase
import com.vas.feature_main_screen.presentation.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule(val context: Context) {

    @Provides
    fun provideContext() : Context {
        return context
    }

    @Provides
    fun provideMainViewModelFactory(getCatsUseCase: GetCatsUseCase,
                                    postLikeUseCase: PostLikeUseCase): MainViewModelFactory {
        return MainViewModelFactory(getCatsUseCase = getCatsUseCase, postLikeUseCase = postLikeUseCase)
    }

    @Provides
    fun provideFavoriteViewModelFactory(getFavoriteUseCase: GetFavoriteUseCase): FavoriteViewModelFactory{
        return FavoriteViewModelFactory(getFavoriteUseCase = getFavoriteUseCase)
    }

}
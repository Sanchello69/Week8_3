package com.vas.week5_3.di

import com.vas.feature_favorite_screen.domain.repository.FavoriteRepository
import com.vas.feature_favorite_screen.domain.useCase.GetFavoriteUseCase
import com.vas.feature_main_screen.domain.repository.MainRepository
import com.vas.feature_main_screen.domain.useCase.GetCatsUseCase
import com.vas.feature_main_screen.domain.useCase.PostLikeUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetMainUseCase(mainRepository: MainRepository): GetCatsUseCase{
        return GetCatsUseCase(mainRepository = mainRepository)
    }

    @Provides
    fun providePostLikeUseCase(mainRepository: MainRepository): PostLikeUseCase {
        return PostLikeUseCase(mainRepository = mainRepository)
    }

    @Provides
    fun provideGetFavoriteUseCase(favoriteRepository: FavoriteRepository): GetFavoriteUseCase{
        return GetFavoriteUseCase(favoriteRepository = favoriteRepository)
    }

}
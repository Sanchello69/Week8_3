package com.vas.week5_3.di

import android.content.Context
import com.vas.feature_favorite_screen.data.local.dao.FavoriteDao
import com.vas.feature_favorite_screen.data.network.api.ApiFavorite
import com.vas.feature_favorite_screen.data.repository.FavoriteRepositoryImpl
import com.vas.feature_favorite_screen.domain.repository.FavoriteRepository
import com.vas.feature_main_screen.data.network.Api
import com.vas.week5_3.network.KtorClient
import com.vas.feature_main_screen.data.repository.MainRepositoryImpl
import com.vas.feature_main_screen.domain.repository.MainRepository
import com.vas.week5_3.local.LocalDatabase
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideApiHelper(): Api {
        return Api(KtorClient.httpClient)
    }

    @Provides
    fun provideMainRepository(api: Api): MainRepository{
        return MainRepositoryImpl(api = api)
    }

    @Provides
    fun provideApiFavoriteHelper(): ApiFavorite {
        return ApiFavorite(KtorClient.httpClient)
    }

    @Provides
    fun provideFavoriteRepository(api: ApiFavorite, dao: FavoriteDao): FavoriteRepository{
        return FavoriteRepositoryImpl(api = api, dao = dao)
    }

    @Provides
    fun provideFavoriteDao(context: Context): FavoriteDao{
        return LocalDatabase.getDatabase(context).getFavoriteDao()
    }

}
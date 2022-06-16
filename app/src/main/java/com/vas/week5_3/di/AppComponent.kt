package com.vas.week5_3.di

import com.vas.feature_favorite_screen.di.FavoriteDeps
import com.vas.feature_favorite_screen.presentation.FavoriteViewModelFactory
import com.vas.feature_main_screen.di.MainDeps
import com.vas.feature_main_screen.navigation.MainNavCommandProvider
import com.vas.feature_main_screen.presentation.MainViewModelFactory
import com.vas.week5_3.MainActivity
import com.vas.week5_3.di.navigation.MainModule
import dagger.Component

@Component(modules = [AppModule::class, DomainModule::class, DataModule::class, MainModule::class])
interface AppComponent : MainDeps, FavoriteDeps {

    override val mainViewModelFactory: MainViewModelFactory
    override val mainNavCommandProvider: MainNavCommandProvider

    override val favoriteViewModelFactory: FavoriteViewModelFactory

    fun inject(mainActivity: MainActivity)
}
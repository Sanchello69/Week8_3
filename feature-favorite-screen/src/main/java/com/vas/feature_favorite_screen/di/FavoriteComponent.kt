package com.vas.feature_favorite_screen.di

import androidx.lifecycle.ViewModel
import com.vas.feature_favorite_screen.presentation.FavoriteFragment
import com.vas.feature_favorite_screen.presentation.FavoriteViewModelFactory
import dagger.Component
import kotlin.properties.Delegates
import kotlin.properties.Delegates.notNull

@Component(dependencies = [FavoriteDeps::class])
internal interface FavoriteComponent {
    fun inject(fragment: FavoriteFragment)

    @Component.Builder
    interface Builder {
        fun deps(deps: FavoriteDeps): Builder

        fun build(): FavoriteComponent
    }
}

interface FavoriteDeps {
    val favoriteViewModelFactory: FavoriteViewModelFactory
}

internal class FavoriteComponentViewModel : ViewModel(){

    val newFavoriteComponent = DaggerFavoriteComponent.builder().deps(FavoriteDepsProvider.deps).build()
}

interface FavoriteDepsProvider {
    val deps: FavoriteDeps

    companion object : FavoriteDepsProvider by FavoriteDepsStore
}

object FavoriteDepsStore : FavoriteDepsProvider {
    override var deps: FavoriteDeps by notNull()
}
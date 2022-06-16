package com.vas.week5_3.navigation

import com.vas.feature_main_screen.navigation.MainNavCommandProvider
import com.vas.navigation.NavCommand
import com.vas.week5_3.R
import javax.inject.Inject

class MainNavCommandProviderImpl @Inject constructor() : MainNavCommandProvider {
    override val toFavorite: NavCommand =
        NavCommand(R.id.action_mainFragment_to_favoriteFragment)
}
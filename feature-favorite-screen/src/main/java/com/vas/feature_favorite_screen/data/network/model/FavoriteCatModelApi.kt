package com.vas.feature_favorite_screen.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FavoriteCatModelApi(

    @SerialName("image")
    val image: ImageCatModelApi,

)

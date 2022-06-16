package com.vas.feature_favorite_screen.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageCatModelApi(
    @SerialName("url")
    val imageId: String,
)

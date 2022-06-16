package com.vas.feature_main_screen.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FavoriteModelApi(

    @SerialName("image_id")
    val imageId: String,

    @SerialName("sub_id")
    val userId: String

)

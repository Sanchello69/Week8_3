package com.vas.feature_favorite_screen.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteModelLocal(
    @PrimaryKey
    @ColumnInfo(name = "image_id")
    val imageId: String
)
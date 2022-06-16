package com.vas.feature_favorite_screen.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vas.feature_favorite_screen.data.local.entity.FavoriteModelLocal

@Dao
interface FavoriteDao {

    @Query("Select * from FavoriteModelLocal")
    fun getFavorite() : LiveData<List<FavoriteModelLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFavorite(listFavorite: List<FavoriteModelLocal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteModelLocal)

}
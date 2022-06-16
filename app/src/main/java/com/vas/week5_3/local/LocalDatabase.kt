package com.vas.week5_3.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vas.feature_favorite_screen.data.local.dao.FavoriteDao
import com.vas.feature_favorite_screen.data.local.entity.FavoriteModelLocal

@Database(entities = [FavoriteModelLocal::class],
    version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun getFavoriteDao(): FavoriteDao

    companion object {
        @Volatile private var instance: LocalDatabase? = null

        fun getDatabase(context: Context): LocalDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, LocalDatabase::class.java, "favorites")
                .fallbackToDestructiveMigration()
                .build()
    }

}
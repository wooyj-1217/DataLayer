package com.wooyj.datalayer.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wooyj.datalayer.data.DATABASE_NAME
import com.wooyj.datalayer.data.DATABASE_VERSION
import com.wooyj.datalayer.data.local.room.dao.BreedDAO
import com.wooyj.datalayer.data.local.room.dao.FavoriteDogDAO
import com.wooyj.datalayer.data.local.room.entity.BreedEntity
import com.wooyj.datalayer.data.local.room.entity.FavoriteDogEntity

@Database(version = DATABASE_VERSION, entities = [BreedEntity::class, FavoriteDogEntity::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun breedDao(): BreedDAO

    abstract fun favoriteDogDao(): FavoriteDogDAO

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { instance = it }
            }
        }
    }
}

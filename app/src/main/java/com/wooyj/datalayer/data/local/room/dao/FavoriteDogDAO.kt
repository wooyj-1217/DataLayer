package com.wooyj.datalayer.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.wooyj.datalayer.data.local.room.entity.FavoriteDogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDogDAO {
    @Query("SELECT * FROM favorite_dog ORDER BY id DESC")
    fun getFavoriteDogList(): Flow<List<FavoriteDogEntity>>

    @Delete
    suspend fun deleteFavoriteDog(data: FavoriteDogEntity)

    @Insert
    suspend fun insertFavoriteDog(data: FavoriteDogEntity)
}

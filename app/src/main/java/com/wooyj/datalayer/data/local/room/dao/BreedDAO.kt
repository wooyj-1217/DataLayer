package com.wooyj.datalayer.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.wooyj.datalayer.data.local.room.entity.BreedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BreedDAO {
    @Insert
    suspend fun insert(data: BreedEntity)

    @Query("SELECT breedName FROM breed")
    fun getBreedList(): Flow<List<String>>

    @Query("SELECT subBreedName FROM breed WHERE breedName = :breedName")
    fun getSubBreedList(breedName: String): Flow<List<String>>
}

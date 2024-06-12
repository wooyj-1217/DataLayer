package com.wooyj.datalayer.data.local.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "favorite_dog",
    foreignKeys = [
        ForeignKey(
            entity = BreedEntity::class,
            parentColumns = ["breedId"],
            childColumns = ["breedId"],
            onDelete = CASCADE,
        ),
    ],
    indices = [Index(value = ["breedId"])], // 인덱스 추가
)
data class FavoriteDogEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val breedId: String,
    val fileName: String,
)

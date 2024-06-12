package com.wooyj.datalayer.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breed")
data class BreedEntity(
    @PrimaryKey val breedId: String,
    val breedName: String,
    val subBreedName: String,
)

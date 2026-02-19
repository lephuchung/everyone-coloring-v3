package com.example.everyonecoloringv3.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artworks")
data class ArtworkEntity(
    @PrimaryKey val id: String,
    val title: String,
    val previewUri: String?,
    val paletteJson: String,
    val gridWidth: Int,
    val gridHeight: Int,
    val mode: String
)

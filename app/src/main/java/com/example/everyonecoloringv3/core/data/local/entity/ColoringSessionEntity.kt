package com.example.everyonecoloringv3.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sessions")
data class ColoringSessionEntity(
    @PrimaryKey val id: String,
    val artworkId: String,
    val mode: String,
    val startedAt: Long,
    val updatedAt: Long
)

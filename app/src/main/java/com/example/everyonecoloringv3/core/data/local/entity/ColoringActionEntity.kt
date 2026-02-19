package com.example.everyonecoloringv3.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "actions")
data class ColoringActionEntity(
    @PrimaryKey val id: String,
    val sessionId: String,
    val x: Int,
    val y: Int,
    val colorIndex: Int,
    val timestamp: Long
)

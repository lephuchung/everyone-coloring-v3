package com.example.everyonecoloringv3.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.everyonecoloringv3.core.data.local.dao.ArtworkDao
import com.example.everyonecoloringv3.core.data.local.dao.ColoringActionDao
import com.example.everyonecoloringv3.core.data.local.dao.ColoringSessionDao
import com.example.everyonecoloringv3.core.data.local.entity.ArtworkEntity
import com.example.everyonecoloringv3.core.data.local.entity.ColoringActionEntity
import com.example.everyonecoloringv3.core.data.local.entity.ColoringSessionEntity

@Database(
    entities = [ArtworkEntity::class, ColoringSessionEntity::class, ColoringActionEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun artworkDao(): ArtworkDao
    abstract fun sessionDao(): ColoringSessionDao
    abstract fun actionDao(): ColoringActionDao
}

package com.example.everyonecoloringv3.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.everyonecoloringv3.core.data.local.entity.ColoringSessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ColoringSessionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ColoringSessionEntity)

    @Query("SELECT * FROM sessions WHERE id = :id")
    fun observeSession(id: String): Flow<ColoringSessionEntity>
}

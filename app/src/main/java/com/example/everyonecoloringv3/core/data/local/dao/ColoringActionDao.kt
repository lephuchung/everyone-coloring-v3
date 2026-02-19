package com.example.everyonecoloringv3.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.everyonecoloringv3.core.data.local.entity.ColoringActionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ColoringActionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ColoringActionEntity)

    @Query("SELECT * FROM actions WHERE sessionId = :sessionId ORDER BY timestamp")
    fun observeActions(sessionId: String): Flow<List<ColoringActionEntity>>
}

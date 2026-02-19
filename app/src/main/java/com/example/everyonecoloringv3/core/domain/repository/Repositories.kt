package com.example.everyonecoloringv3.core.domain.repository

import com.example.everyonecoloringv3.core.domain.model.Artwork
import com.example.everyonecoloringv3.core.domain.model.ColoringAction
import com.example.everyonecoloringv3.core.domain.model.ColoringModeType
import com.example.everyonecoloringv3.core.domain.model.ColoringSession
import kotlinx.coroutines.flow.Flow

interface ArtworkRepository {
    fun getArtworks(): Flow<List<Artwork>>
    suspend fun getArtwork(id: String): Artwork?
    suspend fun saveArtwork(artwork: Artwork)
}

interface SessionRepository {
    suspend fun startSession(artworkId: String, mode: ColoringModeType): ColoringSession
    suspend fun saveAction(sessionId: String, action: ColoringAction)
    fun observeSession(sessionId: String): Flow<ColoringSession>
}

interface PixelArtGenerationRepository {
    suspend fun generatePixelArt(imageUri: String, targetWidth: Int, targetHeight: Int): Artwork
}

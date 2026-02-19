package com.example.everyonecoloringv3.core.domain.usecase

import com.example.everyonecoloringv3.core.domain.coloring.engine.ColoringEngine
import com.example.everyonecoloringv3.core.domain.model.ColoringModeType
import com.example.everyonecoloringv3.core.domain.model.PixelCell
import com.example.everyonecoloringv3.core.domain.repository.ArtworkRepository
import com.example.everyonecoloringv3.core.domain.repository.PixelArtGenerationRepository
import com.example.everyonecoloringv3.core.domain.repository.SessionRepository

class GetArtworksUseCase(private val repository: ArtworkRepository) {
    operator fun invoke() = repository.getArtworks()
}

class StartSessionUseCase(private val repository: SessionRepository) {
    suspend operator fun invoke(artworkId: String, mode: ColoringModeType) =
        repository.startSession(artworkId, mode)
}

class ApplyColorUseCase(
    private val engine: ColoringEngine,
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(cell: PixelCell, colorIndex: Int, sessionId: String) {
        val action = engine.applyColor(cell, colorIndex)
        sessionRepository.saveAction(sessionId, action)
    }
}

class GeneratePixelArtUseCase(private val repository: PixelArtGenerationRepository) {
    suspend operator fun invoke(imageUri: String, targetWidth: Int, targetHeight: Int) =
        repository.generatePixelArt(imageUri, targetWidth, targetHeight)
}

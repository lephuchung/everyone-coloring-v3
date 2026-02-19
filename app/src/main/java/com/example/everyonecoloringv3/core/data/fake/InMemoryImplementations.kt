package com.example.everyonecoloringv3.core.data.fake

import com.example.everyonecoloringv3.core.domain.coloring.engine.ColoringEngine
import com.example.everyonecoloringv3.core.domain.coloring.mode.ColoringMode
import com.example.everyonecoloringv3.core.domain.model.Artwork
import com.example.everyonecoloringv3.core.domain.model.ColoringAction
import com.example.everyonecoloringv3.core.domain.model.ColoringModeType
import com.example.everyonecoloringv3.core.domain.model.ColoringSession
import com.example.everyonecoloringv3.core.domain.model.PixelCell
import com.example.everyonecoloringv3.core.domain.repository.ArtworkRepository
import com.example.everyonecoloringv3.core.domain.repository.PixelArtGenerationRepository
import com.example.everyonecoloringv3.core.domain.repository.SessionRepository
import java.util.UUID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

/**
 * Lightweight in-memory implementations to unblock DI wiring while the real data layer is built.
 */
class InMemoryColoringEngine : ColoringEngine {
    private val state = mutableListOf<PixelCell>()

    override val mode: ColoringMode = object : ColoringMode {
        override val type: ColoringModeType = ColoringModeType.PIXEL
        override fun validateCellSelection(x: Int, y: Int): Boolean = true
    }

    override fun loadArtwork(artwork: Artwork) {
        state.clear()
        repeat(artwork.gridHeight) { y ->
            repeat(artwork.gridWidth) { x ->
                state.add(PixelCell(x, y, colorIndex = 0, isColored = false))
            }
        }
    }

    override fun applyColor(cell: PixelCell, colorIndex: Int): ColoringAction {
        val coloredCell = cell.copy(colorIndex = colorIndex, isColored = true)
        // Replace or add the colored cell in the current state snapshot
        val index = state.indexOfFirst { it.x == cell.x && it.y == cell.y }
        if (index >= 0) state[index] = coloredCell else state.add(coloredCell)
        return ColoringAction(coloredCell, colorIndex, System.currentTimeMillis())
    }

    override fun undo(): ColoringAction? = null

    override fun redo(): ColoringAction? = null

    override fun getCurrentState(): List<PixelCell> = state.toList()
}

class InMemorySessionRepository : SessionRepository {
    private val sessions = MutableStateFlow<Map<String, ColoringSession>>(emptyMap())

    override suspend fun startSession(artworkId: String, mode: ColoringModeType): ColoringSession {
        val now = System.currentTimeMillis()
        val session = ColoringSession(
            id = UUID.randomUUID().toString(),
            artworkId = artworkId,
            mode = mode,
            actions = emptyList(),
            startedAt = now,
            updatedAt = now
        )
        sessions.value = sessions.value + (session.id to session)
        return session
    }

    override suspend fun saveAction(sessionId: String, action: ColoringAction) {
        val current = sessions.value[sessionId] ?: return
        val updated = current.copy(
            actions = current.actions + action,
            updatedAt = System.currentTimeMillis()
        )
        sessions.value = sessions.value + (sessionId to updated)
    }

    override fun observeSession(sessionId: String): Flow<ColoringSession> =
        sessions.map { it[sessionId] }.filterNotNull()
}

class InMemoryArtworkRepository : ArtworkRepository {
    private val artworks = MutableStateFlow<List<Artwork>>(emptyList())

    override fun getArtworks(): Flow<List<Artwork>> = artworks

    override suspend fun getArtwork(id: String): Artwork? = artworks.value.firstOrNull { it.id == id }

    override suspend fun saveArtwork(artwork: Artwork) {
        val filtered = artworks.value.filterNot { it.id == artwork.id }
        artworks.value = filtered + artwork
    }
}

class InMemoryPixelArtGenerationRepository : PixelArtGenerationRepository {
    override suspend fun generatePixelArt(imageUri: String, targetWidth: Int, targetHeight: Int): Artwork {
        // Placeholder pixel art generation; returns an empty artwork with a generated ID.
        return Artwork(
            id = UUID.randomUUID().toString(),
            title = "Generated",
            previewUri = imageUri,
            palette = com.example.everyonecoloringv3.core.domain.model.ColorPalette(emptyList()),
            gridWidth = targetWidth,
            gridHeight = targetHeight,
            mode = ColoringModeType.PIXEL
        )
    }
}

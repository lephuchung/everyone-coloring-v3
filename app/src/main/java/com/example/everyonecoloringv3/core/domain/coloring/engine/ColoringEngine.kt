package com.example.everyonecoloringv3.core.domain.coloring.engine

import com.example.everyonecoloringv3.core.domain.coloring.mode.ColoringMode
import com.example.everyonecoloringv3.core.domain.model.Artwork
import com.example.everyonecoloringv3.core.domain.model.ColoringAction
import com.example.everyonecoloringv3.core.domain.model.PixelCell

interface ColoringEngine {
    val mode: ColoringMode
    fun loadArtwork(artwork: Artwork)
    fun applyColor(cell: PixelCell, colorIndex: Int): ColoringAction
    fun undo(): ColoringAction?
    fun redo(): ColoringAction?
    fun getCurrentState(): List<PixelCell>
}

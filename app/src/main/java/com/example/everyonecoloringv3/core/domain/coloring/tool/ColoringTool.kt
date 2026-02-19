package com.example.everyonecoloringv3.core.domain.coloring.tool

import com.example.everyonecoloringv3.core.domain.model.PixelCell

interface ColoringTool {
    val id: String
    val name: String
    fun onSelect()
    fun onApply(target: PixelCell, colorIndex: Int): List<PixelCell>
}

package com.example.everyonecoloringv3.core.domain.model

data class PixelCell(
    val x: Int,
    val y: Int,
    val colorIndex: Int,
    val isColored: Boolean
)

data class ColoringAction(
    val cell: PixelCell,
    val appliedColorIndex: Int,
    val timestamp: Long
)

data class ColorPalette(val colors: List<Int>)

enum class ColoringModeType { PIXEL, REGION }

data class Artwork(
    val id: String,
    val title: String,
    val previewUri: String?,
    val palette: ColorPalette,
    val gridWidth: Int,
    val gridHeight: Int,
    val mode: ColoringModeType
)

data class ColoringSession(
    val id: String,
    val artworkId: String,
    val mode: ColoringModeType,
    val actions: List<ColoringAction>,
    val startedAt: Long,
    val updatedAt: Long
)

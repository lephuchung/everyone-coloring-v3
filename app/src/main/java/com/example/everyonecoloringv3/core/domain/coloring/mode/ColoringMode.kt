package com.example.everyonecoloringv3.core.domain.coloring.mode

import com.example.everyonecoloringv3.core.domain.model.ColoringModeType

interface ColoringMode {
    val type: ColoringModeType
    fun validateCellSelection(x: Int, y: Int): Boolean
}

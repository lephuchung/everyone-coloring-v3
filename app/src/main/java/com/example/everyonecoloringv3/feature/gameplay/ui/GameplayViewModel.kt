package com.example.everyonecoloringv3.feature.gameplay.ui

import androidx.lifecycle.viewModelScope
import com.example.everyonecoloringv3.core.domain.model.ColorPalette
import com.example.everyonecoloringv3.core.domain.model.PixelCell
import com.example.everyonecoloringv3.core.domain.usecase.ApplyColorUseCase
import com.example.everyonecoloringv3.core.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

data class GameplayState(
    val cells: List<PixelCell> = emptyList(),
    val palette: ColorPalette? = null
)

@HiltViewModel
class GameplayViewModel @Inject constructor(
    private val applyColorUseCase: ApplyColorUseCase,
    ioDispatcher: CoroutineDispatcher
) : BaseViewModel<GameplayState>(GameplayState(), ioDispatcher) {

    fun onCellTap(cell: PixelCell, colorIndex: Int, sessionId: String) {
        viewModelScope.launch(ioDispatcher) {
            applyColorUseCase(cell, colorIndex, sessionId)
        }
    }
}

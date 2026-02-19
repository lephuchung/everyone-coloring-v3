package com.example.everyonecoloringv3.feature.home.ui

import androidx.lifecycle.viewModelScope
import com.example.everyonecoloringv3.core.domain.model.Artwork
import com.example.everyonecoloringv3.core.domain.usecase.GetArtworksUseCase
import com.example.everyonecoloringv3.core.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class HomeState(val artworks: List<Artwork> = emptyList())

@HiltViewModel
class HomeViewModel @Inject constructor(
    getArtworksUseCase: GetArtworksUseCase,
    ioDispatcher: CoroutineDispatcher
) : BaseViewModel<HomeState>(HomeState(), ioDispatcher) {

    val artworks: StateFlow<List<Artwork>> = getArtworksUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
}

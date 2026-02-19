package com.example.everyonecoloringv3.feature.gameplay.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.everyonecoloringv3.core.ui.BaseFragment
import com.example.everyonecoloringv3.databinding.FragmentGameplayBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GameplayFragment : BaseFragment<FragmentGameplayBinding>() {
    private val viewModel: GameplayViewModel by viewModels()

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentGameplayBinding.inflate(inflater, container, false)

    override fun setupUi() {
        // TODO: wire palette selection and canvas tap events
    }

    override fun setupObservers() {
        lifecycleScope.launch {
            viewModel.state.collect {
                binding.pixelCanvas.cells = it.cells
            }
        }
    }
}

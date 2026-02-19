package com.example.everyonecoloringv3.feature.createpixel.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.everyonecoloringv3.core.ui.BaseFragment
import com.example.everyonecoloringv3.databinding.FragmentCreatePixelBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreatePixelFragment : BaseFragment<FragmentCreatePixelBinding>() {
    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentCreatePixelBinding.inflate(inflater, container, false)

    override fun setupUi() {
        // TODO: implement creation UI
    }

    override fun setupObservers() {
        // TODO: wire view model when available
    }
}

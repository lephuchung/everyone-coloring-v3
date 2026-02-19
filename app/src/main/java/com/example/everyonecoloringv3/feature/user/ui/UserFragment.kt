package com.example.everyonecoloringv3.feature.user.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.everyonecoloringv3.core.ui.BaseFragment
import com.example.everyonecoloringv3.databinding.FragmentUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentUserBinding>() {
    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentUserBinding.inflate(inflater, container, false)

    override fun setupUi() {
        // TODO: hook up user-colored artworks list
    }

    override fun setupObservers() {}
}

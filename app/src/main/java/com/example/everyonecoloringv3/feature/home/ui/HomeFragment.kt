package com.example.everyonecoloringv3.feature.home.ui

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.everyonecoloringv3.databinding.FragmentHomeBinding
import com.example.everyonecoloringv3.core.ui.BaseFragment
import com.example.everyonecoloringv3.R
import dagger.hilt.android.AndroidEntryPoint
import com.example.everyonecoloringv3.core.domain.model.Artwork
import com.example.everyonecoloringv3.core.domain.model.ColorPalette
import com.example.everyonecoloringv3.core.domain.model.ColoringModeType

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel: HomeViewModel by viewModels()
    private val adapter = ArtworkAdapter { artwork ->
        // Navigate to gameplay for now when tapping an artwork
        findNavController().navigate(HomeFragmentDirections.actionHomeToGameplay())
    }

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeBinding.inflate(inflater, container, false)

    override fun setupUi() {
        val spacing = resources.getDimensionPixelSize(R.dimen.grid_spacing)
        binding.rvArtworks.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvArtworks.addItemDecoration(GridSpacingItemDecoration(2, spacing, includeEdge = true))
        binding.rvArtworks.adapter = adapter
    }

    private class GridSpacingItemDecoration(
        private val spanCount: Int,
        private val spacing: Int,
        private val includeEdge: Boolean
    ) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            val position = parent.getChildAdapterPosition(view)
            val column = position % spanCount
            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount
                outRect.right = (column + 1) * spacing / spanCount
                if (position < spanCount) outRect.top = spacing
                outRect.bottom = spacing
            } else {
                outRect.left = column * spacing / spanCount
                outRect.right = spacing - (column + 1) * spacing / spanCount
                if (position >= spanCount) outRect.top = spacing
            }
        }
    }

    override fun setupObservers() {
        val sampleArtworks = listOf(
            Artwork(
                id = "1",
                title = "Galaxy Cat",
                previewUri = null,
                palette = ColorPalette(emptyList()),
                gridWidth = 20,
                gridHeight = 20,
                mode = ColoringModeType.PIXEL
            ),
            Artwork(
                id = "2",
                title = "Mountain Sunset",
                previewUri = null,
                palette = ColorPalette(emptyList()),
                gridWidth = 30,
                gridHeight = 30,
                mode = ColoringModeType.PIXEL
            ),
            Artwork(
                id = "3",
                title = "City Lights",
                previewUri = null,
                palette = ColorPalette(emptyList()),
                gridWidth = 25,
                gridHeight = 25,
                mode = ColoringModeType.PIXEL
            )
        )
        adapter.submitList(sampleArtworks)
        binding.emptyView.visibility = View.GONE
        // When real data is ready, replace with viewModel.state.collect { ... }
    }
}

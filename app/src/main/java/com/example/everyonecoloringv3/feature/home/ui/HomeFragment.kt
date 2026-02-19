package com.example.everyonecoloringv3.feature.home.ui

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.everyonecoloringv3.R
import com.example.everyonecoloringv3.core.domain.model.Artwork
import com.example.everyonecoloringv3.core.domain.model.ColorPalette
import com.example.everyonecoloringv3.core.domain.model.ColoringModeType
import com.example.everyonecoloringv3.core.ui.BaseFragment
import com.example.everyonecoloringv3.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel: HomeViewModel by viewModels()
    private val adapter = SectionAdapter { artwork ->
        findNavController().navigate(HomeFragmentDirections.actionHomeToGameplay())
    }

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeBinding.inflate(inflater, container, false)

    override fun setupUi() {
        binding.rvSections.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSections.adapter = adapter
    }

    override fun setupObservers() {
        // Sample sections; replace with real data when available
        val pixelArt = listOf(
            Artwork("1", "Galaxy Cat", null, ColorPalette(emptyList()), 20, 20, ColoringModeType.PIXEL),
            Artwork("2", "Mountain Sunset", null, ColorPalette(emptyList()), 30, 30, ColoringModeType.PIXEL),
            Artwork("3", "City Lights", null, ColorPalette(emptyList()), 25, 25, ColoringModeType.PIXEL)
        )
        val handDrawn = listOf(
            Artwork("4", "Sketch A", null, ColorPalette(emptyList()), 18, 18, ColoringModeType.PIXEL),
            Artwork("5", "Sketch B", null, ColorPalette(emptyList()), 22, 22, ColoringModeType.PIXEL),
            Artwork("6", "Sketch C", null, ColorPalette(emptyList()), 26, 26, ColoringModeType.PIXEL)
        )
        val sections = listOf(
            ArtworkSection(title = "Tranh pixel", items = pixelArt),
            ArtworkSection(title = "Tranh vẽ", items = handDrawn)
        )
        adapter.submitList(sections)
    }
}

private data class ArtworkSection(
    val title: String,
    val items: List<Artwork>
)

private class SectionAdapter(
    private val onClick: (Artwork) -> Unit
) : RecyclerView.Adapter<SectionViewHolder>() {
    private val items = mutableListOf<ArtworkSection>()

    fun submitList(sections: List<ArtworkSection>) {
        items.clear()
        items.addAll(sections)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = com.example.everyonecoloringv3.databinding.ItemArtworkSectionBinding.inflate(inflater, parent, false)
        return SectionViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}

private class SectionViewHolder(
    private val binding: com.example.everyonecoloringv3.databinding.ItemArtworkSectionBinding,
    private val onClick: (Artwork) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private val itemAdapter = ArtworkAdapter(onClick)
    private val spacing = binding.root.resources.getDimensionPixelSize(R.dimen.grid_spacing)
    private val minItemSize = binding.root.resources.getDimensionPixelSize(R.dimen.grid_item_min_size)
    private val gridLayoutManager = GridLayoutManager(binding.root.context, 2)
    private val decoration = GridSpacingItemDecoration(2, spacing, includeEdge = true)

    init {
        binding.rvSectionItems.layoutManager = gridLayoutManager
        if (binding.rvSectionItems.itemDecorationCount == 0) {
            binding.rvSectionItems.addItemDecoration(decoration)
        }
        binding.rvSectionItems.adapter = itemAdapter
    }

    fun bind(section: ArtworkSection) {
        binding.tvSectionTitle.text = section.title
        itemAdapter.submitList(section.items)
        binding.rvSectionItems.doOnLayout { rv ->
            val available = rv.width - rv.paddingLeft - rv.paddingRight
            if (available > 0) {
                val span = (available / minItemSize).coerceIn(2, 6)
                gridLayoutManager.spanCount = span
                decoration.spanCount = span
                (rv as? RecyclerView)?.invalidateItemDecorations()
            }
        }
    }
}

private class GridSpacingItemDecoration(
    var spanCount: Int,
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

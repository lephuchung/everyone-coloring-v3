package com.example.everyonecoloringv3.feature.home.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.everyonecoloringv3.R
import com.example.everyonecoloringv3.core.domain.model.Artwork
import com.example.everyonecoloringv3.databinding.ItemArtworkBinding

class ArtworkAdapter(
    private val onClick: (Artwork) -> Unit
) : ListAdapter<Artwork, ArtworkViewHolder>(Diff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtworkViewHolder {
        val binding = ItemArtworkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtworkViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ArtworkViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private object Diff : DiffUtil.ItemCallback<Artwork>() {
        override fun areItemsTheSame(oldItem: Artwork, newItem: Artwork) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Artwork, newItem: Artwork) = oldItem == newItem
    }
}

class ArtworkViewHolder(
    private val binding: ItemArtworkBinding,
    private val onClick: (Artwork) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Artwork) {
        // Use bundled drawable avatar for sample items
        binding.ivPreview.setImageResource(R.drawable.avatar)
        binding.ivPreview.background = null
        binding.root.setOnClickListener { onClick(item) }
    }
}

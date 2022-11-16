package com.example.moves.presentation.adapters.trailer

import androidx.recyclerview.widget.DiffUtil
import com.example.moves.domain.model.Trailer

class TrailerDiffCallback : DiffUtil.ItemCallback<Trailer>() {
    override fun areItemsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Trailer, newItem: Trailer): Boolean {
        return oldItem == newItem
    }
}
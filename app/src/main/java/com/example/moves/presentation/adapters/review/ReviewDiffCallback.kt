package com.example.moves.presentation.adapters.review

import androidx.recyclerview.widget.DiffUtil
import com.example.moves.domain.model.Review

class ReviewDiffCallback : DiffUtil.ItemCallback<Review>() {
    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
        return oldItem == newItem
    }
}
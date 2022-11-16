package com.example.moves.presentation.adapters.review

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moves.R
import com.example.moves.domain.model.Review
import kotlinx.android.synthetic.main.review_item.view.*

class ReviewRAdapter : ListAdapter<Review, ReviewViewHolder>(ReviewDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.review_item,
            parent,
            false
        )
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {

        val review = getItem(position)

        if (review.title == null) {
            holder.titleReviewTextView.text = "------------"
        } else {
            holder.titleReviewTextView.text = review.title
        }

        holder.reviewTextView.text = review.review

        val backgroundColorId = when {
            review.type == "Позитивный" -> android.R.color.holo_green_light
            review.type == "Негативный" -> android.R.color.holo_red_light
            else -> android.R.color.darker_gray
        }

        val color = ContextCompat.getColor(holder.reviewCardView.context, backgroundColorId)

        holder.reviewCardView.setCardBackgroundColor(color)
    }
}
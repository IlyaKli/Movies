package com.example.moves.presentation.adapters.review

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.review_item.view.*

class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var titleReviewTextView: TextView = itemView.titleReviewTextView

    var reviewTextView = itemView.reviewTextView

    var reviewCardView = itemView.reviewCardView
}
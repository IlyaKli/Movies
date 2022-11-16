package com.example.moves.presentation.adapters.movie

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView = itemView.movieImageView
    var textView: TextView = itemView.ratingTextView
}
package com.example.moves.presentation.adapters.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moves.R
import com.example.moves.domain.model.Movie
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieRAdapter : ListAdapter<Movie, MovieViewHolder>(MovieDiffCallback()) {

    var onReachEndListener: (() -> Unit)? = null

    var movieClickListener: ((movie: Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.movie_item,
            parent,
            false
        )
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)

        Glide.with(holder.imageView)
            .load(movie.poster.url)
            .into(holder.imageView)

        holder.textView.text = movie.rating.kp.toString()

        val rating = movie.rating.kp

        val backgroundId = when  {
            rating > 7.0 -> R.drawable.circle_green
            rating in 5.0..7.0 -> R.drawable.circle_orange
            else -> R.drawable.circle_red
        }
        val background = ContextCompat.getDrawable(holder.textView.context, backgroundId)

        holder.textView.background = background

        holder.imageView.setOnClickListener {
            movieClickListener?.invoke(movie)
        }

        if (position >= currentList.lastIndex - 10) {
            onReachEndListener?.invoke()
        }
    }
}
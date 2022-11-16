package com.example.moves.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moves.R
import com.example.moves.domain.model.Movie
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieRAdapter : RecyclerView.Adapter<MovieRAdapter.MovieViewHolder>() {

    var movies: List<Movie> = ArrayList()

    private lateinit var onReachEndListener: OnReachEndListener

    private lateinit var onClickMovieListener: OnClickMovieListener

    fun setOnReachEndListener(onReachEndListener: OnReachEndListener) {
        this.onReachEndListener = onReachEndListener
    }

    fun setOnClickMovieListener(onClickMovieListener: OnClickMovieListener) {
        this.onClickMovieListener = onClickMovieListener
    }


    fun setMoviesList(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }


    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.movieImageView
        var textView: TextView = itemView.ratingTextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.movie_item,
            parent,
            false
        )
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

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
            onClickMovieListener.onclick(movie)
        }

        if (position >= movies.lastIndex - 10) {
            onReachEndListener.onReachEnd()
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    interface OnReachEndListener {
        fun onReachEnd()
    }

    interface OnClickMovieListener {
        fun onclick(movie: Movie)
    }
}
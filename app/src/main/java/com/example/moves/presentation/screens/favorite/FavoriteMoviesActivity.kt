package com.example.moves.presentation.screens.favorite

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moves.R
import com.example.moves.domain.model.Movie
import com.example.moves.presentation.adapters.favoritemovies.FavoriteMoviesRAdapter
import com.example.moves.presentation.screens.detail.DetailMoveActivity
import kotlinx.android.synthetic.main.activity_favorite_movies.*

class FavoriteMoviesActivity : AppCompatActivity() {

    private val favoriteMoviesViewModel by lazy { ViewModelProvider(this)[FavoriteMoviesViewModel::class.java] }
    private val favoriteMoviesRAdapter by lazy { FavoriteMoviesRAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_movies)
        observer()
        setFavoriteMovieRecyclerView()
    }

    private fun observer() {
        favoriteMoviesViewModel.getFavoriteMovies().observe(this) {
            favoriteMoviesRAdapter.submitList(it)
        }
    }

    private fun setFavoriteMovieRecyclerView() {
        favoriteMovieRecyclerView.layoutManager = GridLayoutManager(this, 2)

        favoriteMovieRecyclerView.adapter = favoriteMoviesRAdapter

        favoriteMoviesRAdapter.movieClickListener = {
            detailActivity(it)
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, FavoriteMoviesActivity::class.java)
        }
    }

    fun detailActivity(movie: Movie) {
        val intent = DetailMoveActivity.newIntent(this, movie)
        startActivity(intent)
    }
}
package com.example.moves.presentation.screens.favorite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.moves.R
import com.example.moves.domain.model.Movie
import com.example.moves.presentation.adapters.favoritemovies.FavoriteMoviesRAdapter
import com.example.moves.presentation.di.MovieApplication
import com.example.moves.presentation.di.ViewModelFactory
import com.example.moves.presentation.screens.detail.DetailMoveActivity
import kotlinx.android.synthetic.main.activity_favorite_movies.*
import javax.inject.Inject

class FavoriteMoviesActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as MovieApplication).component
    }

    private val favoriteMoviesViewModel by lazy { ViewModelProvider(this, viewModelFactory)[FavoriteMoviesViewModel::class.java] }
    private val favoriteMoviesRAdapter by lazy { FavoriteMoviesRAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
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

        favoriteMovieRecyclerView.adapter = favoriteMoviesRAdapter

        favoriteMoviesRAdapter.movieClickListener = {
            detailActivity(it)
        }
    }

    private fun detailActivity(movie: Movie) {
        val intent = DetailMoveActivity.newIntent(this, movie)
        startActivity(intent)
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, FavoriteMoviesActivity::class.java)
        }
    }
}
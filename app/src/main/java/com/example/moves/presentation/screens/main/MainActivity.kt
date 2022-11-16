package com.example.moves.presentation.screens.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moves.R
import com.example.moves.domain.model.Movie
import com.example.moves.presentation.adapters.MovieRAdapter
import com.example.moves.presentation.screens.detail.DetailMoveActivity
import com.example.moves.presentation.screens.favorite.FavoriteMoviesActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mainViewModule by lazy { ViewModelProvider(this)[MainViewModule::class.java] }
    private val movieRAdapter by lazy { MovieRAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observer()
        setMoviesRecyclerView()
    }

    private fun observer() {
        mainViewModule.movies.observe(this) {
            movieRAdapter.setMoviesList(it)
        }

        mainViewModule.isLoading.observe(this) {
            if (it == true) {
                loadingProgressBar.visibility = View.VISIBLE
            }
            else {
                loadingProgressBar.visibility = View.GONE
            }
        }
    }

    private fun setMoviesRecyclerView() {
        movieRecyclerView.adapter = movieRAdapter

        movieRecyclerView.layoutManager = GridLayoutManager(this, 2)

        movieRAdapter.setOnReachEndListener(object : MovieRAdapter.OnReachEndListener {
            override fun onReachEnd() {
                mainViewModule.loadMovie()
            }
        })

        movieRAdapter.setOnClickMovieListener(object : MovieRAdapter.OnClickMovieListener {
            override fun onclick(movie: Movie) {
                detailScreen(movie)
            }
        })
    }

    fun detailScreen(movie: Movie) {
        val intent = DetailMoveActivity.newIntent(this, movie)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.itemFavorite) {
            val intent = FavoriteMoviesActivity.newIntent(this)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}
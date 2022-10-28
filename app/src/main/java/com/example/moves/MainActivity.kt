package com.example.moves

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mainViewModule by lazy { ViewModelProvider(this)[MainViewModule::class.java] }
    private val movieRAdapter by lazy {MovieRAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieRecyclerView.adapter = movieRAdapter

        movieRecyclerView.layoutManager = GridLayoutManager(this, 2)

        mainViewModule.getMoviesLD().observe(this) {
            movieRAdapter.setMoviesList(it)
        }

        mainViewModule.getIsLoadingLD().observe(this) {
            if (it == true) {
                loadingProgressBar.visibility = View.VISIBLE
            }
            else {
                loadingProgressBar.visibility = View.GONE
            }
        }

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
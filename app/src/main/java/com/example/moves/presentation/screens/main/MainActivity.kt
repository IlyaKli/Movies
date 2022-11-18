package com.example.moves.presentation.screens.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moves.R
import com.example.moves.domain.model.Movie
import com.example.moves.presentation.adapters.movie.MovieRAdapter
import com.example.moves.presentation.di.MovieApplication
import com.example.moves.presentation.di.ViewModelFactory
import com.example.moves.presentation.screens.detail.DetailMoveActivity
import com.example.moves.presentation.screens.detail.DetailMovieFragment
import com.example.moves.presentation.screens.favorite.FavoriteMoviesActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy { (application as MovieApplication).component }

    private val mainViewModule by lazy { ViewModelProvider(this, viewModelFactory)[MainViewModule::class.java] }
    private val movieRAdapter by lazy { MovieRAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        swipeRefreshListener()
        observer()
        setMoviesRecyclerView()
    }

    private fun isHorizontalOrientation(): Boolean {
        return mainActivityDetailFragmentContainerView != null
    }

    private fun swipeRefreshListener() {
        movieSwipeRefreshLayout.setOnRefreshListener {
            mainViewModule.loadMovie()
        }
    }

    private fun observer() {
        mainViewModule.movies.observe(this) {
            movieRAdapter.submitList(it)
        }

        mainViewModule.isLoading.observe(this) {
            if (it == false) {
                movieSwipeRefreshLayout.isRefreshing = false
            }
        }
    }

    private fun setMoviesRecyclerView() {
        movieRecyclerView.adapter = movieRAdapter

        movieRecyclerView.layoutManager = GridLayoutManager(this, 2)

        movieRAdapter.onReachEndListener = {
            mainViewModule.loadMovie()
        }

        movieRAdapter.movieClickListener = {
            if (isHorizontalOrientation()) {
                launchFragment(DetailMovieFragment.newInstance(it))
            } else {
                detailScreen(it)
            }
        }
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainActivityDetailFragmentContainerView, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun detailScreen(movie: Movie) {
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
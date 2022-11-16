package com.example.moves.presentation.screens.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.moves.data.database.MovieDatabase
import com.example.moves.data.database.MoviesDao
import com.example.moves.domain.Movie

class FavoriteMoviesViewModel(application: Application) : AndroidViewModel(application) {

    var moviesDao: MoviesDao

    init {
        moviesDao = MovieDatabase.getInstance(application).moviesDao()
    }

    fun getFavoriteMovies() : LiveData<List<Movie>> {
        return moviesDao.getAllMovies()
    }

}
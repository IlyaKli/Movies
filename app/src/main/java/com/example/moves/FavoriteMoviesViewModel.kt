package com.example.moves

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class FavoriteMoviesViewModel(application: Application) : AndroidViewModel(application) {

    var moviesDao: MoviesDao

    init {
        moviesDao = MovieDatabase.getInstance(application).moviesDao()
    }

    fun getFavoriteMovies() : LiveData<List<Movie>> {
        return moviesDao.getAllMovies()
    }

}
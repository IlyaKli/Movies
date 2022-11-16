package com.example.moves.presentation.screens.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.moves.data.database.MovieDatabase
import com.example.moves.data.database.MoviesDao
import com.example.moves.data.repository.MovieRepositoryImpl
import com.example.moves.domain.GetFavoriteMovesListUseCase
import com.example.moves.domain.Movie

class FavoriteMoviesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MovieRepositoryImpl(application)

    private val getFavoriteMovesListUseCase = GetFavoriteMovesListUseCase(repository)

    fun getFavoriteMovies() : LiveData<List<Movie>> {
        return getFavoriteMovesListUseCase()
    }

}
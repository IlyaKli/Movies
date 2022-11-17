package com.example.moves.presentation.screens.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moves.domain.model.Movie
import com.example.moves.domain.usecase.GetFavoriteMovesListUseCase
import javax.inject.Inject

class FavoriteMoviesViewModel @Inject constructor(
    private val getFavoriteMovesListUseCase: GetFavoriteMovesListUseCase
) : ViewModel() {

    fun getFavoriteMovies() : LiveData<List<Movie>> {
        return getFavoriteMovesListUseCase()
    }

}
package com.example.moves.domain.usecase

import com.example.moves.domain.repository.MovieRepository

class GetFavoriteMovesListUseCase(private val repository: MovieRepository) {

    operator fun invoke() = repository.getFavoriteMovesList()
}
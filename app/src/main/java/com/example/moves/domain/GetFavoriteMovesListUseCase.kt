package com.example.moves.domain

class GetFavoriteMovesListUseCase(private val repository: MovieRepository) {

    operator fun invoke() = repository.getFavoriteMovesList()
}
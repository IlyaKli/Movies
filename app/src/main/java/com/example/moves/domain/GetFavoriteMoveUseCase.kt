package com.example.moves.domain

class GetFavoriteMoveUseCase (private val repository: MovieRepository) {

    operator fun invoke(movieId: Int) = repository.getFavoriteMove(movieId)
}
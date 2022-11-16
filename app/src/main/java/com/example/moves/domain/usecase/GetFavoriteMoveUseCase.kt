package com.example.moves.domain.usecase

import com.example.moves.domain.repository.MovieRepository

class GetFavoriteMoveUseCase (private val repository: MovieRepository) {

    operator fun invoke(movieId: Int) = repository.getFavoriteMove(movieId)
}
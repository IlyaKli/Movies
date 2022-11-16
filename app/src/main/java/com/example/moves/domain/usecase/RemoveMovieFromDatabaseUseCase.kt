package com.example.moves.domain.usecase

import com.example.moves.domain.repository.MovieRepository

class RemoveMovieFromDatabaseUseCase (private val repository: MovieRepository) {

    operator fun invoke(movieId: Int) = repository.removeMovieFromDatabase(movieId)
}
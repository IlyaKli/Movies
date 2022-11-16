package com.example.moves.domain

class RemoveMovieFromDatabaseUseCase (private val repository: MovieRepository) {

    operator fun invoke(movieId: Int) = repository.removeMovieFromDatabase(movieId)
}
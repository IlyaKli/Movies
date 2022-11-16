package com.example.moves.domain

class AddMovieInDatabaseUseCase(private val repository: MovieRepository) {

    operator fun invoke(movie: Movie) = repository.addMovieInDatabase(movie)
}
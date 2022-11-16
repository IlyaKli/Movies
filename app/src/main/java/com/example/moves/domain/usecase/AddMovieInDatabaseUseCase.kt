package com.example.moves.domain.usecase

import com.example.moves.domain.model.Movie
import com.example.moves.domain.repository.MovieRepository

class AddMovieInDatabaseUseCase(private val repository: MovieRepository) {

    operator fun invoke(movie: Movie) = repository.addMovieInDatabase(movie)
}
package com.example.moves.domain.usecase

import com.example.moves.domain.model.Movie
import com.example.moves.domain.repository.MovieRepository
import javax.inject.Inject

class AddMovieInDatabaseUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(movie: Movie) = repository.addMovieInDatabase(movie)
}
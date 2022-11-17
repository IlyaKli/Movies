package com.example.moves.domain.usecase

import com.example.moves.domain.repository.MovieRepository
import javax.inject.Inject

class RemoveMovieFromDatabaseUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(movieId: Int) = repository.removeMovieFromDatabase(movieId)
}
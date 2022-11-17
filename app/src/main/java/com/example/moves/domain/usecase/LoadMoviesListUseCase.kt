package com.example.moves.domain.usecase

import com.example.moves.domain.repository.MovieRepository
import javax.inject.Inject

class LoadMoviesListUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(page: Int) = repository.loadMoviesList(page)
}
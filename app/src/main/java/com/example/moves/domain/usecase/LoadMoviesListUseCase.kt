package com.example.moves.domain.usecase

import com.example.moves.domain.repository.MovieRepository

class LoadMoviesListUseCase(private val repository: MovieRepository) {

    operator fun invoke(page: Int) = repository.loadMoviesList(page)
}
package com.example.moves.domain

class LoadMoviesListUseCase(private val repository: MovieRepository) {

    operator fun invoke(page: Int) = repository.loadMoviesList(page)
}
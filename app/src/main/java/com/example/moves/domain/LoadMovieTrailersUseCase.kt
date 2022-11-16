package com.example.moves.domain

class LoadMovieTrailersUseCase (private val repository: MovieRepository) {

    operator fun invoke(movieId: Int) = repository.loadMovieTrailers(movieId)
}
package com.example.moves.domain

class LoadMovieReviewsUseCase (private val repository: MovieRepository) {

    operator fun invoke(movieId: Int) = repository.loadMovieReviews(movieId)
}
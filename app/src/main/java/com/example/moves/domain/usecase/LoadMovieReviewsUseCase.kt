package com.example.moves.domain.usecase

import com.example.moves.domain.repository.MovieRepository

class LoadMovieReviewsUseCase (private val repository: MovieRepository) {

    operator fun invoke(movieId: Int) = repository.loadMovieReviews(movieId)
}
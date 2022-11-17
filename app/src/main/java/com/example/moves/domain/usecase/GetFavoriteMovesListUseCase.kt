package com.example.moves.domain.usecase

import com.example.moves.domain.repository.MovieRepository
import javax.inject.Inject

class GetFavoriteMovesListUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke() = repository.getFavoriteMovesList()
}
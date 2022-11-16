package com.example.moves.domain.repository

import androidx.lifecycle.LiveData
import com.example.moves.domain.model.Movie
import com.example.moves.domain.model.MovieResponse
import com.example.moves.domain.model.ReviewResponse
import com.example.moves.domain.model.TrailerResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface MovieRepository {

    fun loadMoviesList(page: Int) : Single<MovieResponse>

    fun loadMovieTrailers(movieId: Int) : Single<TrailerResponse>

    fun loadMovieReviews(movieId: Int) : Single<ReviewResponse>

    fun getFavoriteMovesList() : LiveData<List<Movie>>

    fun getFavoriteMove(movieId: Int) : LiveData<Movie>

    fun addMovieInDatabase(movie: Movie) : Completable

    fun removeMovieFromDatabase(movieId: Int) : Completable
}
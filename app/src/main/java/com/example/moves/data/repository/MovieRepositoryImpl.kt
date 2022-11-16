package com.example.moves.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.moves.data.database.MovieDatabase
import com.example.moves.data.mapper.MovieMapper
import com.example.moves.data.network.ApiFactory
import com.example.moves.domain.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class MovieRepositoryImpl(application: Application) : MovieRepository {

    private val movieDao = MovieDatabase.getInstance(application).moviesDao()
    private val apiService = ApiFactory.apiService
    private val mapper = MovieMapper()

    override fun loadMoviesList(page: Int): Single<MovieResponse> {
        return apiService.movieLoad(page).map {
            mapper.mapMovieResponseDtoToEntity(it)
        }
    }

    override fun getFavoriteMovesList(): LiveData<List<Movie>> {
        return Transformations.map(movieDao.getAllMovies()) {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override fun getFavoriteMove(movieId: Int): LiveData<Movie> {
        return Transformations.map(movieDao.getMovie(movieId)) {
            if (it == null) return@map null
            else mapper.mapDbModelToEntity(it)
        }
    }

    override fun addMovieInDatabase(movie: Movie): Completable {
        return movieDao.add(mapper.mapEntityToDbModel(movie))
    }

    override fun removeMovieFromDatabase(movieId: Int): Completable {
        return movieDao.remove(movieId)
    }

    override fun loadMovieTrailers(movieId: Int) : Single<TrailerResponse> {
        return apiService.trailerLoad(movieId).map {
            mapper.mapTrailerResponseDtoToEntity(it)
        }
    }

    override fun loadMovieReviews(movieId: Int) : Single<ReviewResponse> {
        return apiService.reviewLoad(movieId).map {
            mapper.mapReviewResponseDtoToEntity(it)
        }
    }
}
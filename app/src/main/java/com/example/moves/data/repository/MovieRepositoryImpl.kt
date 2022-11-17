package com.example.moves.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.moves.data.database.MoviesDao
import com.example.moves.data.mapper.MovieMapper
import com.example.moves.data.network.ApiService
import com.example.moves.domain.model.Movie
import com.example.moves.domain.model.MovieResponse
import com.example.moves.domain.model.ReviewResponse
import com.example.moves.domain.model.TrailerResponse
import com.example.moves.domain.repository.MovieRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieDao: MoviesDao,
    private val apiService: ApiService,
    private val mapper: MovieMapper
) : MovieRepository {

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
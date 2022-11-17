package com.example.moves.data.di

import android.app.Application
import com.example.moves.data.database.MovieDatabase
import com.example.moves.data.database.MoviesDao
import com.example.moves.data.network.ApiFactory
import com.example.moves.data.network.ApiService
import com.example.moves.data.repository.MovieRepositoryImpl
import com.example.moves.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideMoviesDao(application: Application): MoviesDao {
            return MovieDatabase.getInstance(application).moviesDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }
}
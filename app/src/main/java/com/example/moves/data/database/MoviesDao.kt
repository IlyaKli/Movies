package com.example.moves.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.moves.domain.Movie
import io.reactivex.rxjava3.core.Completable

@Dao
interface MoviesDao {

    @Query("SELECT * From movies")
    fun getAllMovies() : LiveData<List<Movie>>

    @Query("SELECT * From movies WHERE id = :id")
    fun getMovie(id: Int) : LiveData<Movie>

    @Insert
    fun add(movie: Movie) : Completable

    @Query("DELETE From movies WHERE id = :id")
    fun remove(id: Int) : Completable
}
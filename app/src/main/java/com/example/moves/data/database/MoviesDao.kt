package com.example.moves.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.moves.data.database.model.MovieDbModel
import io.reactivex.rxjava3.core.Completable

@Dao
interface MoviesDao {

    @Query("SELECT * From movies")
    fun getAllMovies() : LiveData<List<MovieDbModel>>

    @Query("SELECT * From movies WHERE id = :id")
    fun getMovie(id: Int) : LiveData<MovieDbModel>

    @Insert
    fun add(movie: MovieDbModel) : Completable

    @Query("DELETE From movies WHERE id = :id")
    fun remove(id: Int) : Completable
}
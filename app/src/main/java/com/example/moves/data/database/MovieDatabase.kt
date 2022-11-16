package com.example.moves.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moves.data.database.model.MovieDbModel

@Database(entities = [MovieDbModel::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    companion object {
        private var instance: MovieDatabase? = null

        fun getInstance(application: Application): MovieDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    application,
                    MovieDatabase::class.java,
                    "notes.db"
                )
                    .build()
            }
            return instance!!
        }
    }

    abstract fun moviesDao() : MoviesDao
}
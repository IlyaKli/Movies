package com.example.moves.presentation.screens.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moves.data.database.MovieDatabase
import com.example.moves.data.database.MoviesDao
import com.example.moves.data.network.ApiFactory
import com.example.moves.domain.Movie
import com.example.moves.domain.Review
import com.example.moves.domain.Trailer
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers

class DetailMoveViewModel(application: Application) : AndroidViewModel(application) {

    private val compositeDisposable by lazy { CompositeDisposable() }

    private var movieDao: MoviesDao

    var trailers = MutableLiveData<List<Trailer>>()

    var reviews = MutableLiveData<List<Review>>()

    init {
        movieDao = MovieDatabase.getInstance(application).moviesDao()
    }

    fun getFavoriteMovie(movieId: Int) : LiveData<Movie> {
        return movieDao.getMovie(movieId)
    }

    fun insertMovie(movie: Movie) {
        val disposable = movieDao.add(movie)
            .subscribeOn(Schedulers.io())
            .subscribe()
        compositeDisposable.add(disposable)
    }

    fun removeMovie(id: Int) {
        val disposable = movieDao.remove(id)
            .subscribeOn(Schedulers.io())
            .subscribe()
        compositeDisposable.add(disposable)
    }


    fun movieInfoLoad(id: Int) {
        val disposable = ApiFactory.apiService.trailerLoad(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(Function {
                return@Function it.trailsList.trailers
            })
            .subscribe({
                trailers.value = it
                Log.d("trailer", it.toString())
            }, {
                Log.d("ErrorDetailVM", it.message.toString())
            })
        compositeDisposable.add(disposable)

        val reviewDisposable = ApiFactory.apiService.reviewLoad(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                reviews.value = it.reviewList
            }, {
                Log.d("ErrorDetailVM", it.message.toString())

            })
        compositeDisposable.add(reviewDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
package com.example.moves.presentation.screens.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moves.data.repository.MovieRepositoryImpl
import com.example.moves.domain.LoadMoviesListUseCase
import com.example.moves.domain.Movie
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Action
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModule(application: Application) : AndroidViewModel(application) {

    private val repository = MovieRepositoryImpl(application)

    private val loadMoviesListUseCase = LoadMoviesListUseCase(repository)

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val compositeDisposable = CompositeDisposable()

    var page = 1

    init {
        loadMovie()
    }

    fun loadMovie() {
        if (isLoading.value != null && isLoading.value == true) {
            return
        }
        val disposable = loadMoviesListUseCase(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                _isLoading.value = true
            }
            .doAfterTerminate {
                _isLoading.value = false
            }
            .subscribe({
                val loadMovies: MutableList<Movie>? = movies.value as MutableList<Movie>?
                if (loadMovies != null) {
                    loadMovies.addAll(it.movies)
                    _movies.value = loadMovies
                }
                else {
                    _movies.value = it.movies
                }
                page++
            }, {
                Log.d("errorInZapros", it.message.toString())
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
package com.example.moves.presentation.screens.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moves.domain.model.Movie
import com.example.moves.domain.usecase.LoadMoviesListUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainViewModule @Inject constructor (
    private val loadMoviesListUseCase: LoadMoviesListUseCase
        ) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val compositeDisposable = CompositeDisposable()

    private var page = 1

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
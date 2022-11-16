package com.example.moves

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moves.data.network.ApiFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Action
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModule(application: Application) : AndroidViewModel(application) {

    var movies = MutableLiveData<List<Movie>>()
    var isLoading = MutableLiveData<Boolean>(false)
    private val compositeDisposable = CompositeDisposable()
    var page = 1


    init {
        loadMovie()
    }

    fun getMoviesLD() : LiveData<List<Movie>> {
        return movies
    }

    fun getIsLoadingLD() : LiveData<Boolean> {
        return isLoading
    }

    fun loadMovie() {
        if (isLoading.value != null && isLoading.value == true) {
            return
        }
        val disposable = ApiFactory.apiService.movieLoad(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(Consumer {
                isLoading.value = true
            })
            .doAfterTerminate(Action {
                isLoading.value = false
            })
            .subscribe({
                val loadMovies: MutableList<Movie>? = movies.value as MutableList<Movie>?
                if (loadMovies != null) {
                    loadMovies.addAll(it.movies)
                    movies.value = loadMovies
                }
                else {
                    movies.value = it.movies
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
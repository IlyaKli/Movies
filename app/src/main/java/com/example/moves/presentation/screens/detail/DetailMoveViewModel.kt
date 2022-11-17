package com.example.moves.presentation.screens.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moves.domain.model.Movie
import com.example.moves.domain.model.Review
import com.example.moves.domain.model.Trailer
import com.example.moves.domain.usecase.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class DetailMoveViewModel @Inject constructor(
    private val getFavoriteMoveUseCase: GetFavoriteMoveUseCase,
    private val addMovieInDatabaseUseCase: AddMovieInDatabaseUseCase,
    private val removeMovieFromDatabaseUseCase: RemoveMovieFromDatabaseUseCase,
    private val loadMovieTrailersUseCase: LoadMovieTrailersUseCase,
    private val loadMovieReviewsUseCase: LoadMovieReviewsUseCase
) : ViewModel() {

    private val compositeDisposable by lazy { CompositeDisposable() }

    private val _trailers = MutableLiveData<List<Trailer>>()
    val trailers: LiveData<List<Trailer>>
        get() = _trailers

    private val _reviews = MutableLiveData<List<Review>>()
    val reviews: LiveData<List<Review>>
        get() = _reviews

    fun getFavoriteMovie(movieId: Int) : LiveData<Movie> {
        return getFavoriteMoveUseCase(movieId)
    }

    fun insertMovie(movie: Movie) {
        val disposable = addMovieInDatabaseUseCase(movie)
            .subscribeOn(Schedulers.io())
            .subscribe()
        compositeDisposable.add(disposable)
    }

    fun removeMovie(id: Int) {
        val disposable = removeMovieFromDatabaseUseCase(id)
            .subscribeOn(Schedulers.io())
            .subscribe()
        compositeDisposable.add(disposable)
    }


    fun movieInfoLoad(id: Int) {
        val disposable = loadMovieTrailersUseCase(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(Function {
                return@Function it.trailsList.trailers
            })
            .subscribe({
                _trailers.value = it
            }, {
                Log.d("ErrorDetailVM", it.message.toString())
            })
        compositeDisposable.add(disposable)

        val reviewDisposable = loadMovieReviewsUseCase(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _reviews.value = it.reviewList
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
package com.example.moves.data.di

import android.app.Application
import com.example.moves.presentation.screens.detail.DetailMovieFragment
import com.example.moves.presentation.screens.favorite.FavoriteMoviesActivity
import com.example.moves.presentation.screens.main.MainActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(activity: FavoriteMoviesActivity)

    fun inject(fragment: DetailMovieFragment)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}
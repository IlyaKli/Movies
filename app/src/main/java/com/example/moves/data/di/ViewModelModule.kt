package com.example.moves.data.di

import androidx.lifecycle.ViewModel
import com.example.moves.presentation.screens.detail.DetailMoveViewModel
import com.example.moves.presentation.screens.favorite.FavoriteMoviesViewModel
import com.example.moves.presentation.screens.main.MainViewModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModule::class)
    fun bindMainViewModel(viewModel: MainViewModule): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteMoviesViewModel::class)
    fun bindFavoriteMoviesViewModel(viewModel: FavoriteMoviesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailMoveViewModel::class)
    fun bindDetailMoveViewModel(viewModel: DetailMoveViewModel): ViewModel
}
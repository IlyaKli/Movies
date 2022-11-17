package com.example.moves.presentation.di

import android.app.Application
import com.example.moves.data.di.DaggerApplicationComponent

class MovieApplication: Application() {
    val component by lazy { DaggerApplicationComponent.factory().create(this) }
}
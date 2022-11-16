package com.example.moves.domain

import com.google.gson.annotations.SerializedName

class MovieResponse(@SerializedName("docs") val movies: List<Movie>)
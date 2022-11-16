package com.example.moves.domain

import com.example.moves.domain.Movie
import com.google.gson.annotations.SerializedName

class MovieResponse(@SerializedName("docs") val movies: List<Movie>) {
    override fun toString(): String {
        return "MovieResponse(movies=$movies)"
    }
}
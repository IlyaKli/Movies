package com.example.moves.data.network.model

import com.google.gson.annotations.SerializedName

class MovieResponseDto(@SerializedName("docs") val movies: List<MovieDto>)
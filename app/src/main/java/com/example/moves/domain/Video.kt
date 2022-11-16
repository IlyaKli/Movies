package com.example.moves.domain

import com.example.moves.domain.Trailer
import com.google.gson.annotations.SerializedName

class Video(@SerializedName("trailers") val trailers: List<Trailer>)
package com.example.moves.domain

import com.google.gson.annotations.SerializedName

class Video(@SerializedName("trailers") val trailers: List<Trailer>)
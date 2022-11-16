package com.example.moves.data.network.model

import com.google.gson.annotations.SerializedName

class VideoDto(@SerializedName("trailers") val trailers: List<TrailerDto>)
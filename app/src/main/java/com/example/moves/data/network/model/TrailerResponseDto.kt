package com.example.moves.data.network.model

import com.google.gson.annotations.SerializedName

class TrailerResponseDto(@SerializedName("videos") val trailsList: VideoDto)
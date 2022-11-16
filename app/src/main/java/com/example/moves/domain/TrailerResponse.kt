package com.example.moves.domain

import com.example.moves.domain.Video
import com.google.gson.annotations.SerializedName

class TrailerResponse(@SerializedName("videos") val trailsList: Video) {
    override fun toString(): String {
        return "Trailers {videos=$trailsList}"
    }
}
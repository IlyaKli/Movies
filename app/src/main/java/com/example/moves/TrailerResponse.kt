package com.example.moves

import com.google.gson.annotations.SerializedName

class TrailerResponse(@SerializedName("videos") val trailsList: Video) {
    override fun toString(): String {
        return "Trailers {videos=$trailsList}"
    }
}
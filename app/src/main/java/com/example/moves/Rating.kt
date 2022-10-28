package com.example.moves

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Rating(@SerializedName("kp") val kp: Double) : Serializable {
    override fun toString(): String {
        return "Rating(kp='$kp')"
    }
}
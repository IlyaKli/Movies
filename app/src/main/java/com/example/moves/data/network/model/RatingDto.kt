package com.example.moves.data.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class RatingDto(@SerializedName("kp") val kp: Double) : Serializable
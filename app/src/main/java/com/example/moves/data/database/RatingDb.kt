package com.example.moves.data.database

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class RatingDb(@SerializedName("kp") val kp: Double) : Serializable
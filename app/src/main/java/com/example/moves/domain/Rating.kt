package com.example.moves.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Rating(@SerializedName("kp") val kp: Double) : Serializable
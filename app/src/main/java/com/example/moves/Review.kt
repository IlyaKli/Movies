package com.example.moves

import com.google.gson.annotations.SerializedName

class Review(
    @SerializedName("title")
    val title: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("review")
    val review: String,
)
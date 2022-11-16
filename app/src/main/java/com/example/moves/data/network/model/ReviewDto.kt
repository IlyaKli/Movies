package com.example.moves.data.network.model

import com.google.gson.annotations.SerializedName

class ReviewDto(
    @SerializedName("title")
    val title: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("review")
    val review: String,
)
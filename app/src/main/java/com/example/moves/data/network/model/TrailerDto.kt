package com.example.moves.data.network.model

import com.google.gson.annotations.SerializedName

class TrailerDto(

    @SerializedName("name")
    val name: String,

    @SerializedName("url")
    val url: String
)
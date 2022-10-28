package com.example.moves

import com.google.gson.annotations.SerializedName

class Trailer(

    @SerializedName("name")
    val name: String,

    @SerializedName("url")
    val url: String
) {
    override fun toString(): String {
        return "movie {name=$name, url=$url}"
    }
}
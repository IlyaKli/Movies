package com.example.moves.data.network.model

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MovieDto (

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("year")
    val year: Int,

    @SerializedName("poster")
    @Embedded
    val poster: PosterDto,

    @SerializedName("rating")
    @Embedded
    val rating: RatingDto
) : Serializable
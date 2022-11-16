package com.example.moves.data.database.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
class MovieDbModel(

    @PrimaryKey
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
    val poster: PosterDbModel,

    @SerializedName("rating")
    @Embedded
    val rating: RatingDbModel
)
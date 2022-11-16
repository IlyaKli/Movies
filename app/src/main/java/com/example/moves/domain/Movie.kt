package com.example.moves.domain

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "movies")
class Movie(

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
    val poster: Poster,

    @SerializedName("rating")
    @Embedded
    val rating: Rating
) : Serializable {

    override fun toString(): String {
        return "Movie(id=$id, name='$name', description='$description', year=$year, poster=$poster, rating=$rating)"
    }
}
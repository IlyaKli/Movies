package com.example.moves.domain.model

import androidx.room.Embedded
import java.io.Serializable

class Movie(
    val id: Int,
    val name: String,
    val description: String,
    val year: Int,

    @Embedded
    val poster: Poster,

    @Embedded
    val rating: Rating
) : Serializable
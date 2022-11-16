package com.example.moves.domain

import java.io.Serializable

class Poster(val url: String) : Serializable {
    override fun toString(): String {
        return "Poster(url='$url')"
    }
}
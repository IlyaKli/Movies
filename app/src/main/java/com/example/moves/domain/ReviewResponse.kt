package com.example.moves.domain

import com.google.gson.annotations.SerializedName

class ReviewResponse(@SerializedName("docs") val reviewList: List<Review>)
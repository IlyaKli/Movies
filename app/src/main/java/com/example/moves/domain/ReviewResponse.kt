package com.example.moves.domain

import com.example.moves.domain.Review
import com.google.gson.annotations.SerializedName

class ReviewResponse(@SerializedName("docs") val reviewList: List<Review>)
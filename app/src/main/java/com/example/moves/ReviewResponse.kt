package com.example.moves

import com.google.gson.annotations.SerializedName

class ReviewResponse(@SerializedName("docs") val reviewList: List<Review>)
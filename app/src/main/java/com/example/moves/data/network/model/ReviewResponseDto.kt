package com.example.moves.data.network.model

import com.google.gson.annotations.SerializedName

class ReviewResponseDto(@SerializedName("docs") val reviewList: List<ReviewDto>)
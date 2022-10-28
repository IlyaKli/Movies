package com.example.moves

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie?token=RTPA40T-C5CMF17-N98WW8N-S4QQ97W&field=rating.kp&search=0-10&sortField=votes.kp&sortType=-1&limit=30")
    fun movieLoad(@Query("page") page: Int) : Single<MovieResponse>

    @GET("movie?token=RTPA40T-C5CMF17-N98WW8N-S4QQ97W&field=id")
    fun trailerLoad(@Query("search") id: Int) : Single<TrailerResponse>

    @GET("review?token=RTPA40T-C5CMF17-N98WW8N-S4QQ97W&field=movieId")
    fun reviewLoad(@Query("search") movieId: Int) : Single<ReviewResponse>
}
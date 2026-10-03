package com.aksh.crickwatch.data.remote

import com.aksh.crickwatch.data.model.MatchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CricketApi {

    @GET("cricScore")
    suspend fun getCurrentMatches(
        @Query("apikey") apiKey: String,
        @Query("offset") offset: Int = 0
    ): MatchResponse
}
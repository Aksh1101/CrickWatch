package com.aksh.crickwatch.data.repository

import com.aksh.crickwatch.BuildConfig
import com.aksh.crickwatch.data.model.MatchResponse
import com.aksh.crickwatch.data.remote.CricketApi
import jakarta.inject.Inject

class CricketRepository @Inject constructor(
    private val cricketApi: CricketApi
) {

    suspend fun getCricketScores(): MatchResponse {
        return cricketApi.getCurrentMatches(
            apiKey = BuildConfig.CRICKET_API_KEY
        )
    }
}
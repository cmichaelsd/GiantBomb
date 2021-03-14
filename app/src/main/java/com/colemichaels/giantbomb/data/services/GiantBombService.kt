package com.colemichaels.giantbomb.data.services

import com.colemichaels.giantbomb.data.models.GiantBombResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GiantBombService {
    @GET("/api/games")
    suspend fun getGiantBombData(
        @Query("api_key") apiKey: String,
        @Query("filter") filter: String,
        @Query("format") format: String
    ): Response<GiantBombResponse>
}
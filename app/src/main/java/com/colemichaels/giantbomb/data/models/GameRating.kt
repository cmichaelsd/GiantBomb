package com.colemichaels.giantbomb.data.models

import com.squareup.moshi.Json

data class GameRating(
    @Json(name = "api_detail_url")
    val apiDetailUrl: String,
    val id: Int,
    val name: String
)
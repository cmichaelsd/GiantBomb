package com.colemichaels.giantbomb.data.models

import com.squareup.moshi.Json

data class GiantBombResponse(
    val error: String = "",
    val limit: Int = 0,
    val offset: Int = 0,
    @Json(name = "number_of_page_results")
    val numberOfPageResults: Int = 0,
    @Json(name = "number_of_total_results")
    val numberOfTotalResults: Int = 0,
    @Json(name = "status_code")
    val statusCode: Int = 0,
    val results: List<Game> = emptyList(),
    val version: String = ""
)
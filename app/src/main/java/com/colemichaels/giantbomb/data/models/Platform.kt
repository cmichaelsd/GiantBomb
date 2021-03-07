package com.colemichaels.giantbomb.data.models

import com.squareup.moshi.Json

data class Platform(
    @Json(name = "api_detail_url")
    val apiDetailUrl: String,
    val id: Int,
    val name: String,
    @Json(name = "site_detail_url")
    val siteDetailUrl: String,
    val abbreviation: String,
)
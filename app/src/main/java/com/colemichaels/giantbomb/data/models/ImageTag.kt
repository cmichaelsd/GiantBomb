package com.colemichaels.giantbomb.data.models

import com.squareup.moshi.Json

data class ImageTag(
    @Json(name = "api_detail_url")
    val apiDetailUrl: String,
    val name: String,
    val total: Int
)
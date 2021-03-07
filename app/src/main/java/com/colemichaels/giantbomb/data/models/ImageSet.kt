package com.colemichaels.giantbomb.data.models

import com.squareup.moshi.Json

data class ImageSet(
    @Json(name = "icon_url")
    val iconUrl: String?,
    @Json(name = "medium_url")
    val mediumUrl: String?,
    @Json(name = "screen_url")
    val screenUrl: String?,
    @Json(name = "screen_large_url")
    val screenLargeUrl: String?,
    @Json(name = "small_url")
    val smallUrl: String?,
    @Json(name = "super_url")
    val superUrl: String?,
    @Json(name = "thumb_url")
    val thumbUrl: String?,
    @Json(name = "tiny_url")
    val tinyUrl: String?,
    @Json(name = "original_url")
    val originalUrl: String?,
    @Json(name = "image_tags")
    val imageTags: String?,
)
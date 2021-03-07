package com.colemichaels.giantbomb.data.models

import com.squareup.moshi.Json

data class Game(
    val aliases: String?,
    @Json(name = "api_detail_url")
    val apiDetailUrl: String?,
    @Json(name = "date_added")
    val dateAdded: String?,
    val deck: String?,
    val description: String?,
    @Json(name = "expected_release_day")
    val expectedReleaseDay: String?,
    @Json(name = "expected_release_month")
    val expectedReleaseMonth: String?,
    @Json(name = "expected_release_quarter")
    val expectedReleaseQuarter: String?,
    @Json(name = "expected_release_year")
    val expectedReleaseYear: String?,
    val guid: String?,
    val id: Int,
    val image: ImageSet?,
    @Json(name = "image_tags")
    val imageTags: List<ImageTag>?,
    val name: String?,
    @Json(name = "number_of_user_reviews")
    val numberOfUserReviews: Int,
    @Json(name = "original_game_rating")
    val originalGameRating: List<GameRating>?,
    @Json(name = "original_release_date")
    val originalReleaseDate: String?,
    val platforms: List<Platform>?,
    @Json(name = "site_detail_url")
    val siteDetailUrl: String
) {
    val originalBoxImage get() = image?.originalUrl

    val thumbBoxArt get() = image?.thumbUrl
}
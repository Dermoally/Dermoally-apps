package com.polije.dermoally_apps.data.model.disease

import com.squareup.moshi.Json

data class FavoriteResponse(
    @Json(name = "error")
    val error: Boolean,

    @Json(name = "message")
    val message: String,
)

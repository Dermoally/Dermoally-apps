package com.polije.dermoally_apps.data.model.disease

import com.squareup.moshi.Json

data class FavoriteRequest (
    @Json(name = "id_analyze")
    val idAnalyze: String,

    @Json(name = "value")
    val value: Int
)

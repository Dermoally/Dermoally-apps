package com.polije.dermoally_apps.data.disease

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class HistoryResponse(

    @Json(name = "listHistory")
    val listStory: List<History>,

    @Json(name = "error")
    val error: Boolean,

    @Json(name = "message")
    val message: String
)

data class History(

    @Json(name = "photoUrl")
    val photoUrl: String? = null,

    @Json(name = "createdAt")
    val createdAt: String? = null,

    @Json(name = "disease")
    val disease: String? = null,

    @Json(name = "accuracy")
    val accuracy: String? = null,

    @Json(name = "id")
    val id: String,

    @Json(name = "isFavorite")
    val isFavorite: String,

): Serializable
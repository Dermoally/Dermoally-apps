package com.polije.dermoally_apps.data.model.disease

import retrofit2.http.Field

data class FavoriteRequest (
    @Field("id_analyze")
    val username: String,

    @Field("value")
    val value: Int
)
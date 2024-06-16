package com.polije.dermoally_apps.data.disease

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class ResultAnalyze(
    @Json(name = "Acne")
    val acne: Double,
    @Json(name = "ActinicKeratosis")
    val actinicKeratosis: Double,
    @Json(name = "Blackheads")
    val blackheads: Double,
    @Json(name = "Herpes")
    val herpes: Double,
    @Json(name = "Keloid")
    val keloid: Double,
    @Json(name = "KeratosisSeborrheic")
    val keratosisSeborrheic: Double,
    @Json(name = "Milia")
    val milia: Double,
    @Json(name = "Pityriasis versicolor")
    val pityriasisVersicolor: Double,
    @Json(name = "Ringworm")
    val ringworm: Double
): Serializable

package com.polije.dermoally_apps.data.model.disease

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable


data class SkinAnalyzeResponse(
    @Json(name = "error")
    val error: Boolean,

    @Json(name = "data")
    val data: DiseaseDetectionResponse,
)

@JsonClass(generateAdapter = true)
data class DiseaseDetectionResponse(
    @Json(name = "date")
    val date: String,
    @Json(name = "disease_detection")
    val diseaseDetection: DiseaseDetection,
    @Json(name = "image_url")
    val imageUrl: String,
    @Json(name = "result_analyze")
    val resultAnalyze: ResultAnalyze,
    @Json(name = "skin_health")
    val skinHealth: Int
): Serializable
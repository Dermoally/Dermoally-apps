package com.polije.dermoally_apps.data.disease

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

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
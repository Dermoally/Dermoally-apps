package com.polije.dermoally_apps.data.disease

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class DiseaseDetection(
    @Json(name = "id_disease")
    val idDisease: Int,
    @Json(name = "disease")
    val disease: String,
    @Json(name = "confidence")
    val confidence: Double,
    @Json(name = "image_url")
    val imageUrl: String?,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "medication_ingredients")
    val medicationIngredients: List<MedicationIngredient>
): Serializable

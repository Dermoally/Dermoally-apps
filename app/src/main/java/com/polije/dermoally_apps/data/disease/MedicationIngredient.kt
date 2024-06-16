package com.polije.dermoally_apps.data.disease

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class MedicationIngredient(
    @Json(name = "id_disease_medication")
    val idDiseaseMedication: Int,
    @Json(name = "id_medication")
    val idMedication: Int,
    @Json(name = "image_url")
    val imageUrl: String?,
    @Json(name = "link_tokopedia")
    val linkTokopedia: String?,
    @Json(name = "name")
    val name: String
): Serializable


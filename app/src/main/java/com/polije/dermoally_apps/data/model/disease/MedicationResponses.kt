package com.polije.dermoally_apps.data.model.disease

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class MedicationResponses(
    @Json(name = "disease_name")
    val diseaseName: String?,
    @Json(name = "id_disease_medication")
    val idDiseaseMedication: Int,
    @Json(name = "id_medication")
    val idMedication: Int,
    @Json(name = "medication_image_url")
    val imageUrl: String?,
    @Json(name = "link_tokopedia")
    val linkTokopedia: String?,
    @Json(name = "medication_name")
    val name: String
): Serializable
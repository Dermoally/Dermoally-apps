package com.polije.dermoally_apps.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.polije.dermoally_apps.R
import com.polije.dermoally_apps.data.model.disease.DiseaseDetectionResponse
import com.polije.dermoally_apps.data.model.disease.MedicationResponses
import com.polije.dermoally_apps.databinding.ItemMedicationBinding
import com.polije.dermoally_apps.utils.API_URL
import com.polije.dermoally_apps.utils.formatDateString

class MedicationViewHolder(private val binding: ItemMedicationBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(medication: MedicationResponses, onClickSeeMore: (MedicationResponses) -> Unit) {
        Glide.with(binding.root.context).load(API_URL + medication.imageUrl).into(binding.photo)
        binding.disease.text = medication.diseaseName
        binding.medication.text = medication.name

        binding.photo.setBackgroundResource(R.drawable.frame_transparent)
        binding.photo.setClipToOutline(true)

        binding.visit.setOnClickListener {
            onClickSeeMore(medication)
        }
    }
}
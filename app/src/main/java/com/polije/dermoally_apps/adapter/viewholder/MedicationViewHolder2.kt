package com.polije.dermoally_apps.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.polije.dermoally_apps.R
import com.polije.dermoally_apps.data.model.disease.MedicationIngredient
import com.polije.dermoally_apps.databinding.ItemMedication2Binding
import com.polije.dermoally_apps.utils.API_URL

class MedicationViewHolder2(private val binding: ItemMedication2Binding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(medication: MedicationIngredient, onClickSeeMore: (MedicationIngredient) -> Unit) {
        Glide.with(binding.root.context).load(API_URL + medication.imageUrl).into(binding.photo)
        binding.medication.text = medication.name

        binding.photo.setBackgroundResource(R.drawable.frame_transparent)
        binding.photo.setClipToOutline(true)

        binding.visit.setOnClickListener {
            onClickSeeMore(medication)
        }
    }
}
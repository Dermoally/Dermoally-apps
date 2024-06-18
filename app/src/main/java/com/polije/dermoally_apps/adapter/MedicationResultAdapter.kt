package com.polije.dermoally_apps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.polije.dermoally_apps.adapter.viewholder.MedicationViewHolder2
import com.polije.dermoally_apps.data.model.disease.MedicationIngredient
import com.polije.dermoally_apps.databinding.ItemMedication2Binding

class MedicationResultAdapter (
    private var medication: List<MedicationIngredient>,
    private val onClickSeeMore: (MedicationIngredient) -> Unit
) : RecyclerView.Adapter<MedicationViewHolder2>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicationViewHolder2 {
        val binding = ItemMedication2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MedicationViewHolder2(binding)
    }

    override fun getItemCount(): Int = medication.size

    override fun onBindViewHolder(holder: MedicationViewHolder2, position: Int) {
        val medications = medication[position]
        holder.bind(medications, onClickSeeMore)
    }

    fun updateData(newMedication: List<MedicationIngredient>) {
        medication = newMedication
        notifyDataSetChanged()
    }
}
package com.polije.dermoally_apps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.polije.dermoally_apps.adapter.viewholder.MedicationViewHolder
import com.polije.dermoally_apps.data.disease.MedicationResponses
import com.polije.dermoally_apps.databinding.ItemMedicationBinding

class MedicationAdapter (
    private var medication: List<MedicationResponses>,
    private val onClickSeeMore: (MedicationResponses) -> Unit
) : RecyclerView.Adapter<MedicationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicationViewHolder {
        val binding = ItemMedicationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MedicationViewHolder(binding)
    }

    override fun getItemCount(): Int = medication.size

    override fun onBindViewHolder(holder: MedicationViewHolder, position: Int) {
        val medications = medication[position]
        holder.bind(medications, onClickSeeMore)
    }

    fun updateData(newMedication: List<MedicationResponses>) {
        medication = newMedication
        notifyDataSetChanged()
    }
}
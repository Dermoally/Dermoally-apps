package com.polije.dermoally_apps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.polije.dermoally_apps.adapter.viewholder.SkinAnalyzeViewHolder
import com.polije.dermoally_apps.data.disease.DiseaseDetectionResponse
import com.polije.dermoally_apps.databinding.ItemHistoryBinding

class SkinAnalyzeAdapter(private var skinAnalyze: List<DiseaseDetectionResponse>, private val onClickSeeMore: (DiseaseDetectionResponse) -> Unit) : RecyclerView.Adapter<SkinAnalyzeViewHolder>()   {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkinAnalyzeViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SkinAnalyzeViewHolder(binding)
    }

    override fun getItemCount(): Int = skinAnalyze.size

    override fun onBindViewHolder(holder: SkinAnalyzeViewHolder, position: Int) {
        val skinAnalyze =  skinAnalyze[position]
        holder.bind(skinAnalyze, onClickSeeMore)
    }

    fun updateData(newSkinAnalyze: List<DiseaseDetectionResponse>) {
        skinAnalyze = newSkinAnalyze
        notifyDataSetChanged()
    }
}
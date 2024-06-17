package com.polije.dermoally_apps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.polije.dermoally_apps.adapter.viewholder.FavoriteViewHolder
import com.polije.dermoally_apps.adapter.viewholder.HomeViewHolder
import com.polije.dermoally_apps.data.model.disease.DiseaseDetectionResponse
import com.polije.dermoally_apps.databinding.ItemFavoriteBinding
import com.polije.dermoally_apps.databinding.ItemRecentBinding

class HomeAdapter (
    private var skinAnalyze: List<DiseaseDetectionResponse>,
    private val onClickSeeMore: (DiseaseDetectionResponse) -> Unit
) : RecyclerView.Adapter<HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemRecentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int = skinAnalyze.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val history = skinAnalyze[position]
        holder.bind(history, onClickSeeMore)
    }

    fun updateData(newSkinAnalyze: List<DiseaseDetectionResponse>) {
        skinAnalyze = newSkinAnalyze
        notifyDataSetChanged()
    }
}
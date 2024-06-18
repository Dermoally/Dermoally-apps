package com.polije.dermoally_apps.adapter

import android.widget.Filter
import android.widget.Filterable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.polije.dermoally_apps.adapter.viewholder.FavoriteViewHolder
import com.polije.dermoally_apps.data.model.disease.DiseaseDetectionResponse
import com.polije.dermoally_apps.databinding.ItemFavoriteBinding

class FavoriteAdapter(
    private var skinAnalyze: List<DiseaseDetectionResponse>,
    private val onClickSeeMore: (DiseaseDetectionResponse) -> Unit
) : RecyclerView.Adapter<FavoriteViewHolder>(), Filterable {

    private var skinAnalyzeFiltered = skinAnalyze

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val history = skinAnalyzeFiltered[position]
        holder.bind(history, onClickSeeMore)
    }

    override fun getItemCount(): Int = skinAnalyzeFiltered.size

    fun updateData(newSkinAnalyze: List<DiseaseDetectionResponse>) {
        skinAnalyze = newSkinAnalyze
        skinAnalyzeFiltered = newSkinAnalyze
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                skinAnalyzeFiltered = if (charString.isEmpty()) skinAnalyze else {
                    val filteredList = ArrayList<DiseaseDetectionResponse>()
                    skinAnalyze.filter {
                        it.diseaseDetection.disease.contains(charString, true) ||
                                it.favorite.contains(charString, true)
                    }.forEach { filteredList.add(it) }
                    filteredList
                }
                return FilterResults().apply { values = skinAnalyzeFiltered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                skinAnalyzeFiltered = if (results?.values == null) emptyList() else results.values as List<DiseaseDetectionResponse>
                notifyDataSetChanged()
            }
        }
    }
}

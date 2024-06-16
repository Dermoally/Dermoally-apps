package com.polije.dermoally_apps.adapter

import android.widget.Filter
import android.widget.Filterable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.polije.dermoally_apps.adapter.viewholder.SkinAnalyzeViewHolder
import com.polije.dermoally_apps.data.disease.DiseaseDetectionResponse
import com.polije.dermoally_apps.databinding.ItemHistoryBinding

class SkinAnalyzeAdapter(private var skinAnalyze: List<DiseaseDetectionResponse>, private val onClickSeeMore: (DiseaseDetectionResponse) -> Unit) : RecyclerView.Adapter<SkinAnalyzeViewHolder>(), Filterable {

    private var skinAnalyzeFiltered = skinAnalyze

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkinAnalyzeViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SkinAnalyzeViewHolder(binding)
    }

    override fun getItemCount(): Int = skinAnalyzeFiltered.size

    override fun onBindViewHolder(holder: SkinAnalyzeViewHolder, position: Int) {
        val skinAnalyze =  skinAnalyzeFiltered[position]
        holder.bind(skinAnalyze, onClickSeeMore)
    }

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
                        it.diseaseDetection.disease.contains(charString, true)
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

package com.polije.dermoally_apps.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.polije.dermoally_apps.R
import com.polije.dermoally_apps.data.disease.DiseaseDetection
import com.polije.dermoally_apps.data.disease.DiseaseDetectionResponse
import com.polije.dermoally_apps.data.disease.History
import com.polije.dermoally_apps.databinding.ItemHistoryBinding

class SkinAnalyzeViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(skinAnalyze: DiseaseDetectionResponse, onClickSeeMore: (DiseaseDetectionResponse) -> Unit) {
        Glide.with(binding.root.context).load(skinAnalyze.imageUrl).into(binding.photo)
        binding.disease.text = skinAnalyze.diseaseDetection.disease
        binding.createdAt.text = skinAnalyze.date
        val confidencePercentage = skinAnalyze.diseaseDetection.confidence * 100
        binding.accuracy.text = String.format("%.2f%%", confidencePercentage)

        binding.photo.setBackgroundResource(R.drawable.frame_transparent)
        binding.photo.setClipToOutline(true)

        binding.card.setOnClickListener {
            onClickSeeMore(skinAnalyze)
        }

    }
}
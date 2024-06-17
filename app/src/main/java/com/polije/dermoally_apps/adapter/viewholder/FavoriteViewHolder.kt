package com.polije.dermoally_apps.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.polije.dermoally_apps.R
import com.polije.dermoally_apps.data.model.disease.DiseaseDetectionResponse
import com.polije.dermoally_apps.utils.API_URL
import com.polije.dermoally_apps.databinding.ItemFavoriteBinding

class FavoriteViewHolder(private val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(skinAnalyze: DiseaseDetectionResponse, onClickSeeMore: (DiseaseDetectionResponse) -> Unit) {
        Glide.with(binding.root.context).load(API_URL + skinAnalyze.imageUrl).into(binding.photo)
        binding.disease.text = skinAnalyze.diseaseDetection.disease
        val confidencePercentage = skinAnalyze.diseaseDetection.confidence * 100
        binding.accuracy.text = String.format("%.2f%%", confidencePercentage)
        binding.photo.setBackgroundResource(R.drawable.frame_transparent)
        binding.photo.setClipToOutline(true)

        binding.seemore.setOnClickListener {
            onClickSeeMore(skinAnalyze)
        }
    }
}
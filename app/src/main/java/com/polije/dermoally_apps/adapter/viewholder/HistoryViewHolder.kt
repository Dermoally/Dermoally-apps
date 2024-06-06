package com.polije.dermoally_apps.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.polije.dermoally_apps.R
import com.polije.dermoally_apps.data.history.History
import com.polije.dermoally_apps.databinding.ItemHistoryBinding

class HistoryViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(history: History) {
        Glide.with(binding.root.context).load(history.photoUrl).into(binding.photo)
        binding.disease.text = history.disease
        binding.createdAt.text = history.createdAt
        binding.accuracy.text = history.accuracy

        binding.photo.setBackgroundResource(R.drawable.frame_transparent)
        binding.photo.setClipToOutline(true)
    }
}
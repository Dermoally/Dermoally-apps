package com.polije.dermoally_apps.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.polije.dermoally_apps.R
import com.polije.dermoally_apps.data.disease.History
import com.polije.dermoally_apps.databinding.ItemFavoriteBinding

class FavoriteViewHolder(private val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(history: History, onClickSeeMore: (History) -> Unit) {
        Glide.with(binding.root.context).load(history.photoUrl).into(binding.photo)
        binding.disease.text = history.accuracy
        binding.photo.setBackgroundResource(R.drawable.frame_transparent)
        binding.photo.setClipToOutline(true)

        binding.seemore.setOnClickListener {
            onClickSeeMore(history)
        }
    }
}
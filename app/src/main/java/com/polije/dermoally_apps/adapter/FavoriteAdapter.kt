package com.polije.dermoally_apps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.polije.dermoally_apps.adapter.viewholder.FavoriteViewHolder
import com.polije.dermoally_apps.data.disease.History
import com.polije.dermoally_apps.databinding.ItemFavoriteBinding

class FavoriteAdapter(
    private var histories: List<History>,
    private val onClickSeeMore: (History) -> Unit
) : RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val history = histories[position]
        holder.bind(history, onClickSeeMore)
    }

    override fun getItemCount(): Int = histories.size

    fun updateData(newHistories: List<History>) {
        histories = newHistories
        notifyDataSetChanged()
    }
}
package com.polije.dermoally_apps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.polije.dermoally_apps.adapter.viewholder.HistoryViewHolder
import com.polije.dermoally_apps.data.history.History
import com.polije.dermoally_apps.databinding.ItemHistoryBinding

class HistoryAdapter(private var histories: List<History>) : RecyclerView.Adapter<HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val history: History = histories[position]
        holder.bind(history)
    }

    override fun getItemCount(): Int {
        return histories.size
    }

    fun updateData(newHistories: List<History>) {
        histories = newHistories
        notifyDataSetChanged()
    }
}
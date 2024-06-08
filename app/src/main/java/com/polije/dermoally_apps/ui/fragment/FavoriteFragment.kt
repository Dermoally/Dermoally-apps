package com.polije.dermoally_apps.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.polije.dermoally_apps.R
import com.polije.dermoally_apps.adapter.FavoriteAdapter
import com.polije.dermoally_apps.adapter.HistoryAdapter
import com.polije.dermoally_apps.databinding.FragmentFavoriteBinding
import com.polije.dermoally_apps.databinding.FragmentHistoryBinding
import com.polije.dermoally_apps.ui.view.DetailFavoriteActivity
import com.polije.dermoally_apps.utils.DummyFavoriteProvider
import com.polije.dermoally_apps.utils.DummyHistoryProvider

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FavoriteAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val dummyHistories = DummyFavoriteProvider.getDummyFavorite()
        adapter = FavoriteAdapter(dummyHistories){
            val intent = Intent(context, DetailFavoriteActivity::class.java ).apply {
                putExtra("history", it)
            }
            startActivity(intent)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.polije.dermoally_apps.fragment

import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.polije.dermoally_apps.R
import com.polije.dermoally_apps.adapter.HistoryAdapter
import com.polije.dermoally_apps.databinding.FragmentHistoryBinding
import com.polije.dermoally_apps.utils.DummyHistoryProvider

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var historyAdapter: HistoryAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val dummyHistories = DummyHistoryProvider.getDummyHistories()
        historyAdapter = HistoryAdapter(dummyHistories)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = historyAdapter
        val face: Typeface? = ResourcesCompat.getFont(requireContext(), R.font.poppinsregular)
        val searchText = binding.searchView.findViewById<View>(androidx.appcompat.R.id.search_src_text) as TextView
        searchText.typeface = face
        searchText.textSize = 12f
        searchText.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.black))

        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.polije.dermoally_apps.ui.fragment

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.android.ext.android.inject
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.polije.dermoally_apps.R
import com.polije.dermoally_apps.adapter.SkinAnalyzeAdapter
import com.polije.dermoally_apps.data.network.ApiStatus
import com.polije.dermoally_apps.databinding.FragmentHistoryBinding
import com.polije.dermoally_apps.ui.viewmodels.HistoryViewModel
import com.polije.dermoally_apps.utils.showToast

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var skinAnalyzeAdapter: SkinAnalyzeAdapter
    private val viewModel: HistoryViewModel by inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        skinAnalyzeAdapter = SkinAnalyzeAdapter(emptyList()) {
            showToast(requireContext(), it.diseaseDetection.overview)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = skinAnalyzeAdapter

        val face: Typeface? = ResourcesCompat.getFont(requireContext(), R.font.poppinsregular)
        val searchText = binding.searchView.findViewById<View>(androidx.appcompat.R.id.search_src_text) as TextView
        searchText.typeface = face
        searchText.textSize = 12f
        searchText.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.black))

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                skinAnalyzeAdapter.filter.filter(newText)
                return true
            }
        })

        initObserve()
        viewModel.getAllHistory()

        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObserve() {
        viewModel.historyResult.observe(viewLifecycleOwner, Observer { status ->
            when (status) {
                is ApiStatus.Loading -> {
                   binding.progressBar.visibility = View.VISIBLE
                }
                is ApiStatus.Success -> {
                    skinAnalyzeAdapter.updateData(status.data)
                    binding.progressBar.visibility = View.GONE
                }
                is ApiStatus.Error -> {
                    binding.progressBar.visibility = View.GONE
                    showToast(requireContext(), status.message)
                    Log.e("history_fragment", status.message)
                }
            }
        })
    }
}
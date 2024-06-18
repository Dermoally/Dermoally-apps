package com.polije.dermoally_apps.ui.fragment

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.polije.dermoally_apps.R
import com.polije.dermoally_apps.adapter.FavoriteAdapter
import com.polije.dermoally_apps.data.network.ApiStatus
import com.polije.dermoally_apps.databinding.FragmentFavoriteBinding
import com.polije.dermoally_apps.ui.view.ResultActivity
import com.polije.dermoally_apps.ui.viewmodels.FavoriteViewModel
import com.polije.dermoally_apps.utils.showToast
import org.koin.android.ext.android.inject

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: FavoriteAdapter
    private val viewModel: FavoriteViewModel by inject()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        adapter = FavoriteAdapter(emptyList()) {
            val intent = Intent(requireContext(), ResultActivity::class.java)
            intent.putExtra("result", it)
            startActivity(intent)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
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
                adapter.filter.filter(newText)
                return true
            }
        })
        initObserve()
        viewModel.getAllFavorite()
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObserve() {
        viewModel.favoriteResult.observe(viewLifecycleOwner, Observer { status ->
            when (status) {
                is ApiStatus.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is ApiStatus.Success -> {
                    binding.progressBar.visibility = View.GONE
                    adapter.updateData(status.data)
                }
                is ApiStatus.Error -> {
                    binding.progressBar.visibility = View.GONE
                    showToast(requireContext(), status.message)
                    Log.e("favorite_fragment", status.message)
                }
            }
        })
    }
}
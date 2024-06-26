package com.polije.dermoally_apps.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.polije.dermoally_apps.R
import com.polije.dermoally_apps.adapter.FavoriteAdapter
import com.polije.dermoally_apps.adapter.HomeAdapter
import com.polije.dermoally_apps.adapter.MedicationAdapter
import com.polije.dermoally_apps.data.network.ApiStatus
import com.polije.dermoally_apps.databinding.FragmentHomeBinding
import com.polije.dermoally_apps.ui.view.ResultActivity
import com.polije.dermoally_apps.ui.view.ScanPageActivity
import com.polije.dermoally_apps.ui.viewmodels.HomeViewModel
import com.polije.dermoally_apps.utils.showToast
import com.polije.dermoally_apps.utils.startNewActivity
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: HomeAdapter
    private lateinit var medicationAdapter: MedicationAdapter
    private val viewModel: HomeViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.imageView.setBackgroundResource(R.drawable.frame_profile);
        binding.imageView.setClipToOutline(true);

        binding.scanFace.setOnClickListener {
            startNewActivity<ScanPageActivity>(requireContext())
        }

        adapter = HomeAdapter(emptyList()) {
            val intent = Intent(requireContext(), ResultActivity::class.java)
            intent.putExtra("result", it)
            startActivity(intent)
        }
        medicationAdapter = MedicationAdapter(emptyList()) { medication ->
            val searchQuery = "produk+yang+mengandung+${medication.name}"
            val url = "https://www.google.com/search?tbm=shop&q=$searchQuery"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.adapter = adapter

        binding.recyclerViewMedication.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.recyclerViewMedication.adapter = medicationAdapter

        initObserve()
        viewModel.getUserInfo()
        viewModel.getRecentResult()
        viewModel.getAllMedications()

        return root
    }

    private fun initObserve(){
        viewModel.userResult.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ApiStatus.Loading -> {
                   //handle loading
                }
                is ApiStatus.Success -> {
                    binding.nameUserHome.text = it.data.name
                }
                is ApiStatus.Error -> {
                    showToast(requireContext(), it.message)
                    Log.e("home_fragment", it.message)
                }
            }
        })

        viewModel.recentResult.observe(viewLifecycleOwner, Observer { status ->
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
                    Log.e("recent", status.message)
                }
            }
        })

        viewModel.medicationResult.observe(viewLifecycleOwner, Observer { status ->
            when (status) {
                is ApiStatus.Loading -> {
                    binding.progressBarMedication.visibility = View.VISIBLE
                }
                is ApiStatus.Success -> {
                    binding.progressBarMedication.visibility = View.GONE
                    medicationAdapter.updateData(status.data)
                }
                is ApiStatus.Error -> {
                    binding.progressBarMedication.visibility = View.GONE
                    showToast(requireContext(), status.message)
                    Log.e("medication", status.message)
                }
            }
        })
    }
}
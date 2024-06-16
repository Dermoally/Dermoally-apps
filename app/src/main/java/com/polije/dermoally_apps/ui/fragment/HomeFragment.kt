package com.polije.dermoally_apps.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.polije.dermoally_apps.R
import com.polije.dermoally_apps.data.network.ApiStatus
import com.polije.dermoally_apps.databinding.FragmentHomeBinding
import com.polije.dermoally_apps.ui.view.ScanPageActivity
import com.polije.dermoally_apps.ui.viewmodels.HomeViewModel
import com.polije.dermoally_apps.utils.showToast
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.imageView.setBackgroundResource(R.drawable.frame_transparent);
        binding.imageView.setClipToOutline(true);

        binding.scanFace.setOnClickListener {
            val intent = Intent(requireContext(), ScanPageActivity::class.java)
            startActivity(intent)
        }

        initObserve()
        viewModel.getUserInfo()

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
                    showToast(requireContext(), it.errorMessage)
                    Log.e("home_fragment", it.errorMessage)
                }
            }
        })
    }
}
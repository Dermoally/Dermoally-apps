package com.polije.dermoally_apps.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.polije.dermoally_apps.R
import com.polije.dermoally_apps.databinding.FragmentHomeBinding
import com.polije.dermoally_apps.ui.view.AboutActivity
import com.polije.dermoally_apps.ui.view.ScanPageActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

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
        return root
    }

}
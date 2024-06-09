package com.polije.dermoally_apps.ui.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.polije.dermoally_apps.R
import com.polije.dermoally_apps.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.ivProfile.setBackgroundResource(R.drawable.frame_profile);
        binding.ivProfile.setClipToOutline(true);
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
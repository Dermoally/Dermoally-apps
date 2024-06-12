package com.polije.dermoally_apps.ui.fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.polije.dermoally_apps.R
import com.polije.dermoally_apps.databinding.FragmentSettingBinding
import com.polije.dermoally_apps.ui.view.AboutActivity
import com.polije.dermoally_apps.ui.view.AppInfoActivity
import com.polije.dermoally_apps.ui.view.EditProfileActivity
import com.polije.dermoally_apps.ui.view.HomePageActivity
import com.polije.dermoally_apps.ui.view.OurTeamActivity

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.ivProfile.setBackgroundResource(R.drawable.frame_profile);
        binding.ivProfile.setClipToOutline(true);
        binding.appsInfo.setOnClickListener {
            val intent = Intent(requireContext(), AppInfoActivity::class.java)
            startActivity(intent)
        }
        binding.about.setOnClickListener {
            val intent = Intent(requireContext(), AboutActivity::class.java)
            startActivity(intent)
        }
        binding.ourTeam.setOnClickListener {
            val intent = Intent(requireContext(), OurTeamActivity::class.java)
            startActivity(intent)
        }
        binding.editProfile.setOnClickListener {
            val intent = Intent(requireContext(), EditProfileActivity::class.java)
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
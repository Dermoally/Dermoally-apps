package com.polije.dermoally_apps.ui.fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.polije.dermoally_apps.R
import com.polije.dermoally_apps.data.network.ApiStatus
import com.polije.dermoally_apps.databinding.FragmentSettingBinding
import com.polije.dermoally_apps.ui.view.AboutActivity
import com.polije.dermoally_apps.ui.view.AppInfoActivity
import com.polije.dermoally_apps.ui.view.EditProfileActivity
import com.polije.dermoally_apps.ui.view.HomePageActivity
import com.polije.dermoally_apps.ui.view.LoginActivity
import com.polije.dermoally_apps.ui.view.OurTeamActivity
import com.polije.dermoally_apps.ui.viewmodels.AuthViewModel
import com.polije.dermoally_apps.ui.viewmodels.SettingViewModel
import com.polije.dermoally_apps.utils.showToast
import com.polije.dermoally_apps.utils.startNewActivity
import org.koin.android.ext.android.inject

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SettingViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.ivProfile.setBackgroundResource(R.drawable.frame_profile);
        binding.ivProfile.setClipToOutline(true);

        binding.appsInfo.setOnClickListener {
            startNewActivity<AppInfoActivity>(requireContext())
        }
        binding.about.setOnClickListener {
            startNewActivity<AboutActivity>(requireContext())
        }
        binding.ourTeam.setOnClickListener {
            startNewActivity<OurTeamActivity>(requireContext())
        }
        binding.editProfile.setOnClickListener {
            startNewActivity<EditProfileActivity>(requireContext())
        }
        binding.logout.setOnClickListener {
            viewModel.logout()
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish()
            showToast(requireContext(), "You have successfully logged out")
        }

        initObserve()
        viewModel.getUserInfo()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObserve() {
        viewModel.userResult.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ApiStatus.Loading -> {
                    //handle loading
                }
                is ApiStatus.Success -> {
                    binding.tvName.text = it.data.name
                    binding.tvUsername.text = it.data.username
                }
                is ApiStatus.Error -> {
                    showToast(requireContext(), it.message)
                    Log.e("home_fragment", it.message)
                }
            }
        })
    }
}
package com.polije.dermoally_apps.ui.view

import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.polije.dermoally_apps.R
import com.polije.dermoally_apps.data.model.user.UserRequest
import com.polije.dermoally_apps.data.network.ApiStatus
import com.polije.dermoally_apps.databinding.ActivityDetailFavoriteBinding
import com.polije.dermoally_apps.databinding.ActivityEditProfileBinding
import com.polije.dermoally_apps.databinding.FragmentSettingBinding
import com.polije.dermoally_apps.ui.viewmodels.ProfileViewModel
import com.polije.dermoally_apps.ui.viewmodels.SettingViewModel
import com.polije.dermoally_apps.utils.showToast
import org.koin.android.ext.android.inject

class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding

    private val viewModel: ProfileViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.userProfile.setBackgroundResource(R.drawable.frame_profile);
        binding.userProfile.setClipToOutline(true);

        binding.btnCancel.setOnClickListener{
            onBackPressed()
        }

        binding.btnSave.setOnClickListener {
            val name = binding.name.text.toString()
            val email = binding.userEmail.text.toString()

            val request = UserRequest(email, name)
            if (name.isEmpty()){
                binding.name.error = "Name is required"
            } else if (email.isEmpty()) {
                binding.userEmail.error = "Email is required"
            } else {
                viewModel.updateUser(request)
            }
        }

        initObserve()
        viewModel.getUserInfo()
    }

    private fun initObserve() {
        viewModel.userResult.observe(this, Observer {
            when (it) {
                is ApiStatus.Loading -> {
                    //handle loading
                }
                is ApiStatus.Success -> {
                    binding.userName.text = SpannableStringBuilder(it.data.username)
                    binding.userEmail.text = SpannableStringBuilder(it.data.email)
                    binding.name.text = SpannableStringBuilder(it.data.name)
                }
                is ApiStatus.Error -> {
                    showToast(this, it.message)
                    Log.e("profile", it.message)
                }
            }
        })
        viewModel.updateUserResult.observe(this, Observer {
            when (it) {
                is ApiStatus.Loading -> {
                    binding.loadingOverlay.visibility = View.VISIBLE
                }
                is ApiStatus.Success -> {
                    binding.loadingOverlay.visibility = View.GONE
                    showToast(this, "Profile updated successfully")
                    viewModel.getUserInfo()
                }
                is ApiStatus.Error -> {
                    binding.loadingOverlay.visibility = View.GONE
                    showToast(this, it.message)
                    Log.e("profile_update", it.message)
                }

                else -> binding.loadingOverlay.visibility = View.GONE
            }
        })


    }
}


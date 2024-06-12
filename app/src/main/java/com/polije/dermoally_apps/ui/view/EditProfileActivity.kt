package com.polije.dermoally_apps.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.polije.dermoally_apps.R
import com.polije.dermoally_apps.databinding.ActivityDetailFavoriteBinding
import com.polije.dermoally_apps.databinding.ActivityEditProfileBinding
import com.polije.dermoally_apps.databinding.FragmentSettingBinding

class EditProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.userProfile.setBackgroundResource(R.drawable.frame_profile);
        binding.userProfile.setClipToOutline(true);

        binding.btnCancel.setOnClickListener{
            startActivity(Intent(this, FragmentSettingBinding::class.java))
        }
    }
}


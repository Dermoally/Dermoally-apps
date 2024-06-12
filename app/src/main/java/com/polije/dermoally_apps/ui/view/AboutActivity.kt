package com.polije.dermoally_apps.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.polije.dermoally_apps.R
import com.polije.dermoally_apps.databinding.ActivityAboutBinding
import com.polije.dermoally_apps.databinding.ActivityResultBinding
import com.polije.dermoally_apps.databinding.FragmentSettingBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnBack.setOnClickListener{
            startActivity(Intent(this, FragmentSettingBinding::class.java))
        }
    }
}
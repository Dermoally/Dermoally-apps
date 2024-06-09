package com.polije.dermoally_apps.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.polije.dermoally_apps.R
import com.polije.dermoally_apps.databinding.ActivityHomePageBinding
import com.polije.dermoally_apps.databinding.ActivityResultBinding
import com.polije.dermoally_apps.databinding.ActivityScanPageBinding
import com.polije.dermoally_apps.ui.fragment.HomeFragment

class ResultActivity : AppCompatActivity() {
    private lateinit var binding : ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnBackToHome.setOnClickListener{
            startActivity(Intent(this, HomePageActivity::class.java))
        }

        binding.userScan.setBackgroundResource(R.drawable.frame_result);
        binding.userScan.setClipToOutline(true);
    }
}
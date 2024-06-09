package com.polije.dermoally_apps.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.polije.dermoally_apps.R
import com.polije.dermoally_apps.databinding.ActivityHomePageBinding
import com.polije.dermoally_apps.databinding.ActivityScanPageBinding

class ScanPageActivity : AppCompatActivity() {
    private lateinit var binding : ActivityScanPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityScanPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgScan.setBackgroundResource(R.drawable.custom_transparant);
        binding.imgScan.setClipToOutline(true);

        binding.btnDone.setOnClickListener{
            startActivity(Intent(this, ResultActivity::class.java))
        }

        binding.btnBack.setOnClickListener{
            startActivity(Intent(this, HomePageActivity::class.java))
        }
    }
}
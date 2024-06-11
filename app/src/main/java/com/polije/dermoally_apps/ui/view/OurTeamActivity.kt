package com.polije.dermoally_apps.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.polije.dermoally_apps.R
import com.polije.dermoally_apps.databinding.ActivityAboutBinding
import com.polije.dermoally_apps.databinding.ActivityOurTeamBinding
import com.polije.dermoally_apps.databinding.FragmentSettingBinding

class OurTeamActivity : AppCompatActivity() {
    private lateinit var binding : ActivityOurTeamBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityOurTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnBack.setOnClickListener{
            startActivity(Intent(this, FragmentSettingBinding::class.java))
        }
    }
}
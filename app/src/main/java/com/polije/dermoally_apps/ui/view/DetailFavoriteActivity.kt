package com.polije.dermoally_apps.ui.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.polije.dermoally_apps.R
import com.polije.dermoally_apps.data.disease.History
import com.polije.dermoally_apps.databinding.ActivityDetailFavoriteBinding
import com.polije.dermoally_apps.databinding.ActivityHomePageBinding

class DetailFavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailFavoriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        val history = intent.getSerializableExtra("history") as? History
        history?.let {
            binding.tvDisease.text = it.disease
            binding.tvCreatedAt.text = it.createdAt
            binding.tvAccuracy.text = it.accuracy
            Glide.with(this).load(history.photoUrl).into(binding.ivPhoto)
        }
    }
}
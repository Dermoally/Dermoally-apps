package com.polije.dermoally_apps.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import org.koin.android.ext.android.inject
import com.polije.dermoally_apps.R
import com.polije.dermoally_apps.adapter.MedicationResultAdapter
import com.polije.dermoally_apps.data.model.disease.DiseaseDetectionResponse
import com.polije.dermoally_apps.data.model.disease.FavoriteRequest
import com.polije.dermoally_apps.data.model.disease.MedicationIngredient
import com.polije.dermoally_apps.data.network.ApiStatus
import com.polije.dermoally_apps.databinding.ActivityResultBinding
import com.polije.dermoally_apps.ui.viewmodels.ResultViewModel
import com.polije.dermoally_apps.utils.API_URL
import com.polije.dermoally_apps.utils.formatDateString
import com.polije.dermoally_apps.utils.showToast

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private lateinit var medicationAdapter: MedicationResultAdapter
    private val viewModel: ResultViewModel by inject()
    private var isFavorite: Boolean = false
    private var currentAnalyzeId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnBackToHome.setOnClickListener {
            startActivity(Intent(this, HomePageActivity::class.java))
        }

        binding.userScan.setBackgroundResource(R.drawable.frame_result)
        binding.userScan.setClipToOutline(true)

        val result = intent.getSerializableExtra("result") as? DiseaseDetectionResponse
        result?.let {
            currentAnalyzeId = it.idAnalyze
            Glide.with(this).load(API_URL + result.imageUrl).into(binding.userScan)
            binding.diseaseName.text = it.diseaseDetection.disease
            val formattedDate = formatDateString(it.date, "yyyy-MM-dd HH:mm:ss", "dd MMMM yyyy")
            binding.date.text = formattedDate
            binding.diseaseDesc.text = it.diseaseDetection.overview
            binding.skinHealth.text = buildString {
                append(it.skinHealth.toString())
                append("%")
            }

            isFavorite = it.favorite == "1"
            updateFavoriteIcon()

            binding.imageView4.setOnClickListener {
                currentAnalyzeId?.let { id ->
                    val newFavoriteStatus = if (isFavorite) 0 else 1
                    val favoriteRequest = FavoriteRequest(id, newFavoriteStatus)
                    viewModel.updateFavorite(favoriteRequest)
                }
            }

            setupRecyclerView(it.diseaseDetection.medicationIngredients)
        }

        viewModel.updateFavoriteResult.observe(this, Observer { status ->
            when (status) {
                is ApiStatus.Loading -> {
                    binding.loadingOverlay.visibility = View.VISIBLE
                }
                is ApiStatus.Success -> {
                    binding.loadingOverlay.visibility = View.GONE
                    isFavorite = !isFavorite
                    updateFavoriteIcon()
                    showToast(this, "Favorite status updated successfully")
                }
                is ApiStatus.Error -> {
                    binding.loadingOverlay.visibility = View.GONE
                    showToast(this, "Failed to update favorite status: ${status.message}")
                }

                else -> binding.loadingOverlay.visibility = View.GONE
            }
        })
    }

    private fun updateFavoriteIcon() {
        if (isFavorite) {
            binding.imageView4.setImageResource(R.drawable.baseline_favorite_24)
        } else {
            binding.imageView4.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    private fun setupRecyclerView(medications: List<MedicationIngredient>) {
        medicationAdapter = MedicationResultAdapter(medications) { medication ->
            val searchQuery = "produk+yang+mengandung+${medication.name}"
            val url = "https://www.google.com/search?tbm=shop&q=$searchQuery"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ResultActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = medicationAdapter
        }
    }
}

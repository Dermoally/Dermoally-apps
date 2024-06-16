package com.polije.dermoally_apps.ui.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.polije.dermoally_apps.R
import com.polije.dermoally_apps.data.network.ApiStatus
import com.polije.dermoally_apps.databinding.ActivityHomePageBinding
import com.polije.dermoally_apps.databinding.ActivityScanPageBinding
import com.polije.dermoally_apps.ui.viewmodels.SkinAnalyzeViewModel
import com.polije.dermoally_apps.utils.showToast
import com.yalantis.ucrop.UCrop
import java.io.File
import java.util.Date
import org.koin.android.ext.android.inject


class ScanPageActivity : AppCompatActivity() {
    private lateinit var binding : ActivityScanPageBinding
    private var currentImage: Uri? = null
    private val viewModel: SkinAnalyzeViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityScanPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.imgScan.setBackgroundResource(R.drawable.custom_transparant);
        binding.imgScan.setClipToOutline(true);

        binding.btnDone.setOnClickListener{
            if (currentImage != null){
                viewModel.uploadSkinAnalyze(currentImage!!)
            } else {
                showToast(this, "Please select an image to analyze")
            }
        }

        binding.btnBack.setOnClickListener{
            onBackPressed()
        }

        binding.btnGallery.setOnClickListener {
            launcherGallery.launch(
                PickVisualMediaRequest(
                    ActivityResultContracts.PickVisualMedia.ImageOnly
                )
            )
        }
        initObserver()
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImage = uri
            withUcrop(currentImage!!)
        }
    }

    private fun withUcrop(uri: Uri) {
        val timestamp = Date().time
        val cachedImage =
            File(cacheDir, "img_${timestamp}.jpg")

        val destinationUri = Uri.fromFile(cachedImage)

        val uCrop = UCrop.of(uri, destinationUri).withAspectRatio(1f, 1f)

        uCrop.getIntent(this).apply {
            launcherUCrop.launch(this)
        }
    }

    private fun setPreview() {
        binding.imgScan.setImageURI(currentImage)
    }

    private val launcherUCrop =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val resultUri = UCrop.getOutput(result.data!!)
                if (resultUri != null) {
                    currentImage = resultUri
                    setPreview()
                }
            }
        }

    private fun initObserver() {
        viewModel.uploadSkinAnalyzeResult.observe(this) { result ->
            binding.apply {
                when (result) {
                    is ApiStatus.Loading -> {
                        binding.loadingOverlay.visibility = View.VISIBLE
                    }

                    is ApiStatus.Success -> {
                        binding.loadingOverlay.visibility = View.GONE
                        val intent = Intent(this@ScanPageActivity, ResultActivity::class.java)
                        intent.putExtra("result", result.data)
                        startActivity(intent)
                    }

                    is ApiStatus.Error -> {
                        binding.loadingOverlay.visibility = View.GONE
                       showToast(this@ScanPageActivity, result.errorMessage)
                    }

                    else -> binding.loadingOverlay.visibility = View.GONE
                }
            }
        }
    }
}
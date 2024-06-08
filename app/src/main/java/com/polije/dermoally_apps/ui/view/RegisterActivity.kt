package com.polije.dermoally_apps.ui.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.polije.dermoally_apps.R
import com.polije.dermoally_apps.databinding.ActivityRegisterBinding
import com.polije.dermoally_apps.ui.viewmodels.RegisterViewModel
import org.koin.android.ext.android.inject

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onBindView()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun onBindView() {
        binding.buttonRegister.setOnClickListener(this)
        binding.tvLogin.setOnClickListener(this)
        binding.etPasswordRegister.setOnTouchListener { _, event ->
            val drawableEnd = 2
            if (event.action == MotionEvent.ACTION_UP &&
                event.rawX >= (binding.etPasswordRegister.right - binding.etPasswordRegister.compoundDrawables[drawableEnd].bounds.width())
            ) {
                togglePasswordVisibility()
                true
            } else false
        }
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.buttonRegister -> {
                //
            }
            binding.tvLogin -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            else -> {
                //
            }
        }
    }


    private fun togglePasswordVisibility() {
        val currentInputType = binding.etPasswordRegister.inputType
        if (currentInputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            binding.etPasswordRegister.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.etPasswordRegister.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_off_24, 0)
        } else {
            binding.etPasswordRegister.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.etPasswordRegister.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_24, 0)
        }
        binding.etPasswordRegister.typeface = ResourcesCompat.getFont(this, R.font.poppinsregular)
        binding.etPasswordRegister.setSelection(binding.etPasswordRegister.text.length)
    }
}
package com.polije.dermoally_apps.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import android.view.View
import androidx.activity.enableEdgeToEdge
import org.koin.android.ext.android.inject
import androidx.core.content.res.ResourcesCompat
import com.polije.dermoally_apps.R
import com.polije.dermoally_apps.databinding.ActivityLoginBinding
import com.polije.dermoally_apps.viewmodels.LoginViewModel

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onBindView();
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun onBindView() {
        binding.buttonLogin.setOnClickListener(this)
        binding.tvRegister.setOnClickListener(this)
        binding.buttonLoginGoogle.setOnClickListener(this)
        binding.etPasswordLogin.setOnTouchListener { _, event ->
            val drawableEnd = 2
            if (event.action == MotionEvent.ACTION_UP &&
                event.rawX >= (binding.etPasswordLogin.right - binding.etPasswordLogin.compoundDrawables[drawableEnd].bounds.width())
            ) {
                togglePasswordVisibility()
                true
            } else false
        }
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.buttonLogin -> {
                val intent = Intent(this, HomePageActivity::class.java)
                startActivity(intent)
            }
            binding.tvRegister -> {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
            else -> {
                // Case default
            }
        }
    }


    private fun togglePasswordVisibility() {
        val currentInputType = binding.etPasswordLogin.inputType
        if (currentInputType == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            binding.etPasswordLogin.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            binding.etPasswordLogin.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_off_24, 0)
        } else {
            binding.etPasswordLogin.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            binding.etPasswordLogin.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_24, 0)
        }
        binding.etPasswordLogin.typeface = ResourcesCompat.getFont(this, R.font.poppinsregular)
        binding.etPasswordLogin.setSelection(binding.etPasswordLogin.text.length)
    }

}
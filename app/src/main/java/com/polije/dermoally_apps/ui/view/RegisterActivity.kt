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
import com.polije.dermoally_apps.data.auth.RegisterRequest
import com.polije.dermoally_apps.data.network.ApiStatus
import com.polije.dermoally_apps.databinding.ActivityRegisterBinding
import com.polije.dermoally_apps.ui.viewmodels.RegisterViewModel
import com.polije.dermoally_apps.utils.showToast
import com.polije.dermoally_apps.utils.startNewActivity
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
        initObserve()
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
                val username = binding.etUsernameRegister.text.toString().trim()
                val name = binding.etNameRegister.text.toString().trim()
                val email = binding.etEmailRegister.text.toString().trim()
                val password = binding.etPasswordRegister.text.toString().trim()

                val request = RegisterRequest(
                    name, username, email, password
                )

                if (username.isEmpty()) {
                    binding.etUsernameRegister.error = "username is required"
                } else if (name.isEmpty()) {
                    binding.etNameRegister.error = "name is required"
                } else if (email.isEmpty()) {
                    binding.etEmailRegister.error = "email is required"
                } else if (password.isEmpty()) {
                    binding.etPasswordRegister.error = "password is required"
                } else if (password.length < 8) {
                    binding.etPasswordRegister.error = "password must be more than 8 characters"
                } else {
                    viewModel.register(request)
                }
            }
            binding.tvLogin -> {
                startNewActivity<LoginActivity>(this)
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

    private fun initObserve(){
        viewModel.registerResult.observe(this){
            when(it) {
                is ApiStatus.Loading -> {
                    binding.loadingOverlay.visibility = View.VISIBLE
                }
                is ApiStatus.Success -> {
                    binding.loadingOverlay.visibility = View.GONE
                    showToast(this, it.data.message,)
                    startNewActivity<LoginActivity>(this, finishCurrent = true)
                }

                is ApiStatus.Error -> {
                    binding.loadingOverlay.visibility = View.GONE
                    showToast(this, it.errorMessage)
                }
            }
        }
    }
}
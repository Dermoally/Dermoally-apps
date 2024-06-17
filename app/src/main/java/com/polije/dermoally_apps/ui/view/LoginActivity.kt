package com.polije.dermoally_apps.ui.view

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
import com.polije.dermoally_apps.data.auth.LoginRequest
import com.polije.dermoally_apps.data.network.ApiStatus
import com.polije.dermoally_apps.databinding.ActivityLoginBinding
import com.polije.dermoally_apps.ui.viewmodels.LoginViewModel
import com.polije.dermoally_apps.utils.showToast
import com.polije.dermoally_apps.utils.startNewActivity

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onBindView()
        initObserve()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun onBindView() {
        binding.buttonLogin.setOnClickListener(this)
        binding.tvRegister.setOnClickListener(this)
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
                val username = binding.etUsernameLogin.text.toString().trim()
                val password = binding.etPasswordLogin.text.toString().trim()
                val request = LoginRequest(username, password)
                if (username.isEmpty()) {
                    binding.etUsernameLogin.error = "username is required"
                } else if (password.isEmpty()) {
                    binding.etPasswordLogin.error = "password is required"
                } else if (password.length < 8) {
                    binding.etPasswordLogin.error = "password must be more than 8 characters"
                } else {
                    viewModel.login(request)
                }
            }
            binding.tvRegister -> {
                startNewActivity<RegisterActivity>(this)
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

    private fun initObserve(){
        viewModel.loginResult.observe(this){
            when(it) {
                is ApiStatus.Loading -> {
                    binding.loadingOverlay.visibility = View.VISIBLE
                }
                is ApiStatus.Success -> {
                    binding.loadingOverlay.visibility = View.GONE
                    showToast(this, it.data.message)
                    startNewActivity<HomePageActivity>(this, finishCurrent = true)
                }

                is ApiStatus.Error -> {
                    binding.loadingOverlay.visibility = View.GONE
                    showToast(this, it.message)
                }
            }
        }
    }

}
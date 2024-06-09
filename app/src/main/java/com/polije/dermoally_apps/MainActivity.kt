package com.polije.dermoally_apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Observer
import com.polije.dermoally_apps.ui.view.HomePageActivity
import com.polije.dermoally_apps.ui.view.LoginActivity
import com.polije.dermoally_apps.ui.viewmodels.AuthViewModel
import com.polije.dermoally_apps.ui.viewmodels.LoginViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val splashTimeOut: Long = 3000
    private val viewModel: AuthViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel.isLogin.observe(this, Observer { isLoggedIn ->
            if (isLoggedIn) {
                startActivity(Intent(this@MainActivity, HomePageActivity::class.java))
            } else {
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            }
            finish()
        })

        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.checkLoginStatus()
        }, splashTimeOut)
    }
}
package com.polije.dermoally_apps.ui.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.polije.dermoally_apps.R
import com.polije.dermoally_apps.databinding.ActivityHomePageBinding
import com.polije.dermoally_apps.ui.fragment.FavoriteFragment
import com.polije.dermoally_apps.ui.fragment.HistoryFragment
import com.polije.dermoally_apps.ui.fragment.HomeFragment
import com.polije.dermoally_apps.ui.fragment.SettingFragment

class HomePageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
        binding.bottomNavView.background = null
        binding.bottomNavView.menu.getItem(2).isEnabled = false
        binding.floatingButton.setColorFilter(Color.parseColor("#020852"))

        replaceFragment(HomeFragment())

        binding.bottomNavView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.ic_home -> replaceFragment(HomeFragment())
                R.id.ic_setting -> replaceFragment(SettingFragment())
                R.id.ic_fav -> replaceFragment(FavoriteFragment())
                R.id.ic_history -> replaceFragment(HistoryFragment())
            }
            true
        }

        binding.floatingButton.setOnClickListener{
            startActivity(Intent(this, ScanPageActivity::class.java))
        }
    }


    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameFragment, fragment)
        fragmentTransaction.commit()
    }
}
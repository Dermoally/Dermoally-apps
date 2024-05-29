package com.polije.dermoally_apps.view

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.polije.dermoally_apps.R
import com.polije.dermoally_apps.databinding.ActivityHomePageBinding
import com.polije.dermoally_apps.fragment.FavoriteFragment
import com.polije.dermoally_apps.fragment.HistoryFragment
import com.polije.dermoally_apps.fragment.HomeFragment
import com.polije.dermoally_apps.fragment.SettingFragment

class HomePageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
    }


    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameFragment, fragment)
        fragmentTransaction.commit()
    }
}
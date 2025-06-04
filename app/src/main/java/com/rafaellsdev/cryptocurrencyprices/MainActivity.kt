package com.rafaellsdev.cryptocurrencyprices

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rafaellsdev.cryptocurrencyprices.databinding.ActivityMainBinding
import com.rafaellsdev.cryptocurrencyprices.feature.home.view.fragments.FavoritesFragment
import com.rafaellsdev.cryptocurrencyprices.feature.home.view.fragments.FeedFragment
import com.rafaellsdev.cryptocurrencyprices.feature.home.view.fragments.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupBottomNavigation()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, HomeFragment())
                .commit()
        }
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            val fragment: Fragment = when (item.itemId) {
                R.id.nav_home -> HomeFragment()
                R.id.nav_favorites -> FavoritesFragment()
                R.id.nav_feed -> FeedFragment()
                else -> HomeFragment()
            }
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, fragment)
                .commit()
            true
        }
    }
}

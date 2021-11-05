package com.example.movieapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapp.R
import com.example.movieapp.adapter.ViewPagerAdapter
import com.example.movieapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

val fragmentsArray = arrayOf(
    "Movies",
    "Series",
)

class MainActivity : AppCompatActivity() {

    // API Key
    // a195e377afe07079b5ccdf8d794572ce

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        val viewPager = binding.viewPager2
        val tabLayout = binding.tabLayout

        val adapter = ViewPagerAdapter(supportFragmentManager,lifecycle)
        viewPager.adapter = adapter



        TabLayoutMediator(tabLayout, viewPager){
            tab, position -> tab.text = fragmentsArray[position]
        }.attach()


    }
}




package com.gaspardeelias.exoplayerverticalslider

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.gaspardeelias.exoplayerverticalslider.databinding.ActivityMainBinding
import com.gaspardeelias.exoplayerverticalslider.ui.pager.VideoListingFragment

class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, VideoListingFragment())
            .addToBackStack(null)
            .commit()
    }
}
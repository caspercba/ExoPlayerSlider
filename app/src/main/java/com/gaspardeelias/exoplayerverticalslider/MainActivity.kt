package com.gaspardeelias.exoplayerverticalslider

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.gaspardeelias.exoplayerverticalslider.databinding.ActivityMainBinding
import com.gaspardeelias.exoplayerverticalslider.ui.pager.VideoListingFragment
import com.gaspardeelias.exoplayerverticalslider.ui.profile.ProfileFragment

class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, VideoListingFragment())
            .commit()
    }

    override fun onResume() {
        super.onResume()
        setupViews()
    }

    fun setupViews() {
        binding.home.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, VideoListingFragment())
                .commit()
        }
        binding.profile.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProfileFragment())
                .commit()
        }
    }
}
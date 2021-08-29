package com.gaspardeelias.exoplayerverticalslider.ui.pager

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gaspardeelias.repo.model.Video

class VideoFragmentStateAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private var videos: List<Video>? = null

    override fun getItemCount() = videos?.size ?: 0

    override fun createFragment(position: Int): Fragment {
        return VideoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(VideoFragment.PARAM_VIDEO, videos!![position])
            }
        }
    }

    fun setVideos(videos: List<Video>) {
        this.videos = videos
        notifyDataSetChanged()
    }

}
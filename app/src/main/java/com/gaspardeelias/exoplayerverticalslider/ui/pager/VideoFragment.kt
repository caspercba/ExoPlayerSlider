package com.gaspardeelias.exoplayerverticalslider.ui.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gaspardeelias.exoplayerverticalslider.MainApplication
import com.gaspardeelias.exoplayerverticalslider.databinding.FragmentVideoBinding
import com.gaspardeelias.exoplayerverticalslider.utils.ExoPlayerUtils
import com.gaspardeelias.repo.model.Video
import com.google.android.exoplayer2.SimpleExoPlayer

class VideoFragment : Fragment() {

    lateinit var binding: FragmentVideoBinding
    var video: Video? = null
    lateinit var playerProvider: PlayerProvider

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideoBinding.inflate(inflater, container, false)
        video = arguments?.getParcelable(PARAM_VIDEO)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playerProvider = (activity?.application as MainApplication).getPlayerProvider()
    }

    override fun onPause() {
        super.onPause()
        playerProvider.onPause()
    }

    override fun onResume() {
        super.onResume()
        playerProvider.attachPlayer(binding.playerView, video?.url)
        binding.playerView.useArtwork = true
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        const val PARAM_VIDEO = "param_video"
        const val VIDEO_POS = "video_pos"
        const val DEMO_VIDEO_URL = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4"
    }
}
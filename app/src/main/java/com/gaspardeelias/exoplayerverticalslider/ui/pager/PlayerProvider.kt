package com.gaspardeelias.exoplayerverticalslider.ui.pager

import android.content.Context
import androidx.core.view.isVisible
import com.gaspardeelias.exoplayerverticalslider.utils.ExoPlayerUtils
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.StyledPlayerView

class PlayerProvider(context: Context) {

    private var player: SimpleExoPlayer = SimpleExoPlayer.Builder(context).build()
    private var oldPlayerView: StyledPlayerView? = null

    fun onPause() {
        player.stop()
    }

    fun attachPlayer(playerView: StyledPlayerView, url: String?) {
        if (oldPlayerView != playerView && url != null) {
            playerView?.let {
                it.isVisible = true
                StyledPlayerView.switchTargetView(player, oldPlayerView, playerView)
                player.pause()
                player.prepare(ExoPlayerUtils.buildMediaSource(it.context, url))
                oldPlayerView = playerView
                player.play()
            }
        }
    }
}
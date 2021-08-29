package com.gaspardeelias.exoplayerverticalslider.utils

import android.content.Context
import android.net.Uri
import com.gaspardeelias.exoplayerverticalslider.R
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Util

object ExoPlayerUtils {
    fun buildMediaSource(context: Context, url: String): MediaSource {
        val userAgent =
            Util.getUserAgent(
                context,
                context.getString(R.string.app_name)
            )

        val dataSourceFactory = DefaultHttpDataSourceFactory(userAgent)
        val hlsMediaSource =
            ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(url))

        return hlsMediaSource
    }
}
package com.gaspardeelias.exoplayerverticalslider

import android.app.Application
import com.gaspardeelias.exoplayerverticalslider.ui.pager.PlayerProvider
import com.gaspardeelias.exoplayerverticalslider.utils.SSLUtils
import com.gaspardeelias.repo.VideoRepository
import com.gaspardeelias.repo.VideoRepositoryImpl
import com.google.android.exoplayer2.Player

class MainApplication: Application() {

    private var repository: VideoRepository? = null
    private var playerProvider: PlayerProvider? = null

    override fun onCreate() {
        super.onCreate()
        SSLUtils.installCerts()
    }

    fun getRepository(): VideoRepository {
        if(repository == null) repository = VideoRepositoryImpl(this)
        return repository!!
    }

    fun getPlayerProvider(): PlayerProvider {
        if(playerProvider == null) playerProvider = PlayerProvider(this)
        return playerProvider!!
    }
}
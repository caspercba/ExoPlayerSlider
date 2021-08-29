package com.gaspardeelias.exoplayerverticalslider

import android.app.Application
import com.gaspardeelias.exoplayerverticalslider.ui.pager.PlayerProvider
import com.gaspardeelias.exoplayerverticalslider.utils.SSLUtils
import com.gaspardeelias.repo.Repository
import com.gaspardeelias.repo.RepositoryImpl

class MainApplication: Application() {

    private var repository: Repository? = null
    private var playerProvider: PlayerProvider? = null

    override fun onCreate() {
        super.onCreate()
        SSLUtils.installCerts()
    }

    fun getRepository(): Repository {
        if(repository == null) repository = RepositoryImpl(this)
        return repository!!
    }

    fun getPlayerProvider(): PlayerProvider {
        if(playerProvider == null) playerProvider = PlayerProvider(this)
        return playerProvider!!
    }
}
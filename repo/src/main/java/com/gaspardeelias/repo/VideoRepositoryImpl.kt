package com.gaspardeelias.repo

import android.content.Context
import com.gaspardeelias.repo.datasource.FileDataSource
import com.gaspardeelias.repo.model.Video

class VideoRepositoryImpl(context: Context): VideoRepository {

    private val fileDataSource = FileDataSource(context)

    override suspend fun getVideos(): List<Video> {
        val dataFromFileSystem = fileDataSource.getVideoList()
        return dataFromFileSystem
    }
}
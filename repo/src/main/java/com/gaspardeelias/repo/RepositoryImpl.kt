package com.gaspardeelias.repo

import android.content.Context
import com.gaspardeelias.repo.datasource.FileDataSource
import com.gaspardeelias.repo.model.User
import com.gaspardeelias.repo.model.Video
import com.gaspardeelias.repo.model.VideoList
import com.google.gson.Gson

class RepositoryImpl(context: Context) : Repository {

    private val fileDataSource = FileDataSource(context)

    override suspend fun getVideos(): List<Video> {
        val jsonString = fileDataSource.loadFileFromAssets(FILE_VIDEO)
        return if (jsonString != null) Gson().fromJson(
            jsonString,
            VideoList::class.java
        ) else arrayListOf()
    }

    override suspend fun getUser(): User {
        val jsonString = fileDataSource.loadFileFromAssets(FILE_USER)
        return Gson().fromJson(jsonString, User::class.java)
    }

    companion object {
        private const val FILE_VIDEO = "videos.json"
        private const val FILE_USER = "user.json"
    }
}
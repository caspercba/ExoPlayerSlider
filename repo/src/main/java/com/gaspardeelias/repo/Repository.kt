package com.gaspardeelias.repo

import com.gaspardeelias.repo.model.User
import com.gaspardeelias.repo.model.Video

interface Repository {
    suspend fun getVideos(): List<Video>
    suspend fun getUser(): User
}
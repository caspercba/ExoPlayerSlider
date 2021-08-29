package com.gaspardeelias.repo

import com.gaspardeelias.repo.model.Video

interface VideoRepository {
    suspend fun getVideos(): List<Video>
}
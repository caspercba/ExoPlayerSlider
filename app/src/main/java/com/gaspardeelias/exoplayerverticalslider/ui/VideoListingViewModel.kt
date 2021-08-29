package com.gaspardeelias.exoplayerverticalslider.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gaspardeelias.repo.VideoRepository
import com.gaspardeelias.repo.model.Video
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VideoListingViewModel(val repo: VideoRepository) : ViewModel() {
    private val _data = MutableLiveData<List<Video>>()

    val data: LiveData<List<Video>>
        get() = _data

    fun refresh() {
        var videos: List<Video>?
        CoroutineScope(Dispatchers.IO).launch {
            videos = repo.getVideos()
            CoroutineScope(Dispatchers.Main).launch {
                _data.value = videos!!
            }
        }
    }

    class ViewModelFactory(val repo: VideoRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return VideoListingViewModel(repo) as T
        }

    }

}
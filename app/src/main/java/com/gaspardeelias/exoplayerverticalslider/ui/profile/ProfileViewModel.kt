package com.gaspardeelias.exoplayerverticalslider.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gaspardeelias.exoplayerverticalslider.ui.VideoListingViewModel
import com.gaspardeelias.repo.Repository
import com.gaspardeelias.repo.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(val repo: Repository) : ViewModel() {

    private val _data = MutableLiveData<User>()
    val data: LiveData<User>
        get() = _data


    fun refresh() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = repo.getUser()
            CoroutineScope(Dispatchers.Main).launch {
                _data.value = result
            }
        }

    }
}

class ProfileViewModelProvider(val repo: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ProfileViewModel(repo) as T
}
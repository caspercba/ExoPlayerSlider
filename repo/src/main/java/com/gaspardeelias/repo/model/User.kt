package com.gaspardeelias.repo.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("user_name")
    val userName: String,
    @SerializedName("user_title")
    val userTitle: String,
    @SerializedName("user_local_image")
    val userLocalImage: String,
    @SerializedName("user_videos")
    val userVideos: Int,
    @SerializedName("user_following")
    val userFollowing: Int,
    @SerializedName("user_fans")
    val userFans: Int,
    @SerializedName("user_hearts")
    val userHearts: Int
)
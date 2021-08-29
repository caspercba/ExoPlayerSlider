package com.gaspardeelias.repo.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Video(
    @SerializedName("video_description")
    val desc: String?,
    @SerializedName("video_path")
    val url: String,
    @SerializedName("video_number_likes")
    val likes: Int = 0,
    @SerializedName("video_number_comments")
    val comments: Int = 0,
    @SerializedName("user_id")
    val userId: String?,
    @SerializedName("user_name")
    val userName: String?,
    @SerializedName("user_image_path")
    val userImagePath: String?
) : Parcelable
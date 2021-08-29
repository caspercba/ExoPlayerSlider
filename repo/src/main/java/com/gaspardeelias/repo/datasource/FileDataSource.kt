package com.gaspardeelias.repo.datasource

import android.content.Context
import com.gaspardeelias.repo.model.Video
import com.gaspardeelias.repo.model.VideoList
import com.google.gson.Gson
import java.io.IOException

class FileDataSource(val context: Context) {

    suspend fun getVideoList(): List<Video> {
        val jsonString = loadFileFromAssets(context, FILE_SOURCE)
        if(jsonString != null) {
            return Gson().fromJson(jsonString, VideoList::class.java)
        } else {
            return arrayListOf()
        }
    }

    private fun loadFileFromAssets(context: Context, filename: String): String? {
        var json: String? = null
        try {
            val inputS = context.assets.open(filename)
            val size = inputS.available()
            val buffer = ByteArray(size)
            inputS.read(buffer)
            inputS.close()
            json = String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return json
    }

    companion object {
        private const val FILE_SOURCE = "videos.json"
    }
}
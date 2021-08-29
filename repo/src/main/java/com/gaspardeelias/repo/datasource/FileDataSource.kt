package com.gaspardeelias.repo.datasource

import android.content.Context
import com.gaspardeelias.repo.model.Video
import com.gaspardeelias.repo.model.VideoList
import com.google.gson.Gson
import java.io.IOException

class FileDataSource(val context: Context) {

    suspend fun loadFileFromAssets(filename: String): String? {
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
}
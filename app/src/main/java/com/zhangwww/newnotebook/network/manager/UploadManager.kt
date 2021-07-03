package com.zhangwww.networkmodule.manager

import com.zhangwww.networkmodule.process.UploadListener
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.util.concurrent.TimeUnit

object UploadManager {

    // 上传进度监听
    private var uploadListener: UploadListener? = null

    private val uploadMap = hashMapOf<String, String>()

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()


    fun upload(url: String, file: File, downloadListener: UploadListener? = null) {
        uploadMap[file.absolutePath] = url
        this.uploadListener = downloadListener
        // 构建请求

        val request = Request.Builder().url(url).build()
        val response = okHttpClient.newCall(request).execute()

    }

}
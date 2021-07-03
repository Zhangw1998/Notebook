package com.zhangwww.networkmodule.manager

import android.util.Log
import com.zhangwww.networkmodule.process.DownloadListener
import com.zhangwww.networkmodule.process.ProgressInterceptor
import com.zhangwww.networkmodule.process.ProgressListener
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit

object DownloadManager {

    // 下载进度监听
    private var downloadListener: DownloadListener? = null

    // 下载列表, key表示文件绝对路径, value表示下载连接
    private val downloadMap = hashMapOf<String, String>()

    @Volatile
    private var cancelDownload = false

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .addInterceptor(ProgressInterceptor(object : ProgressListener {
            override fun update(bytesRead: Long, contentLength: Long, done: Boolean) {
                downloadListener?.invoke(bytesRead, contentLength, done)
            }
        }))
        .build()

    fun download(url: String, file: File, downloadListener: DownloadListener? = null) {
        if (!file.exists()) {
            file.parentFile?.mkdirs()
        }
        downloadMap[file.absolutePath] = url
        this.downloadListener = downloadListener
        // 构建请求
        val request = Request.Builder().url(url).build()
        val response = okHttpClient.newCall(request).execute()
        if (response.isSuccessful && response.body != null) {
            writeToFile(response, file)
        }
    }

    fun cancelDownload() {
        cancelDownload = true
    }


    private fun writeToFile(response: Response, file: File) {
        if (!file.exists()) {
            file.parentFile?.mkdirs()
        }
        val inputStream = response.body!!.byteStream()
        val outputStream = FileOutputStream(file)
        val buff = ByteArray(1024 * 4)
        try {
            while (true) {
                if (cancelDownload) {
                    file.delete()
                    return
                }
                val read = inputStream.read(buff)
                if (read == -1) {
                    break
                }
                outputStream.write(buff, 0, read)
            }
        } catch (e: Exception) {
            Log.e(DownloadManager, "writeToFile: ${e.localizedMessage}")
        } finally {
            inputStream.close()
            outputStream.close()
            response.body?.close()
        }
    }


    private const val DownloadManager = "DownloadManager"
}
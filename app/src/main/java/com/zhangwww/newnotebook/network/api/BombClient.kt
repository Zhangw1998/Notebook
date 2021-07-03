package com.zhangwww.newnotebook.network.api

import android.util.Log
import com.zhangwww.newnotebook.data.TestData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object BombClient {

    var secretKey: String = "在bomb上创建项目的secretKey"

    const val BASE_URL: String = "https://api2.bmob.cn"

    // 时间单位
    private val TIME_UNIT = TimeUnit.SECONDS

    // 连接超时时长
    private const val CONNECT_TIME_OUT = 15L

    // 读取超时时长
    private const val READ_TIME_OUT = 15L

    // 写入超时时长
    private const val WRITE_TIME_OUT = 15L

    val bombApi: BombApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(BmobConverterFactory.create())
        .client(
            OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIME_OUT, TIME_UNIT)
                .readTimeout(READ_TIME_OUT, TIME_UNIT)
                .writeTimeout(WRITE_TIME_OUT, TIME_UNIT)
                .retryOnConnectionFailure(true)
                .addInterceptor(BmobInterceptor())
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
        )
        .build()
        .create(BombApi::class.java)

    const val TAG = "BombClient"

}

suspend fun BombClient.getTest(objectId: String) {
    try {
        val response = bombApi.getTest(objectId)
        Log.d(TAG, "getTest: $response")
    } catch (e: Exception) {
        Log.e(TAG, "getTest: ", e)
    }
}

suspend fun BombClient.postTest(testData: TestData) {
    val response = bombApi.postTest(testData)
    Log.d(TAG, "postTest: $response")
}

suspend fun BombClient.putTest(objectId: String, testData: TestData) {
    val response = bombApi.updateTest(objectId, testData)
    Log.d(TAG, "putTest: $response")
}

suspend fun BombClient.deleteTest(objectId: String) {
    val response = bombApi.deleteTest(objectId)
    Log.d(TAG, "deleteTest: $response")
}
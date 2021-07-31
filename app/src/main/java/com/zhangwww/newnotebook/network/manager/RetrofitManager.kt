package com.zhangwww.newnotebook.network.manager

import android.util.Log
import com.zhangwww.newnotebook.network.factory.CallFactoryProxy
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitManager {

    private const val TAG = "RetrofitManager"

    private val defaultRetrofit by lazy {
        buildRetrofit(Config.defaultBaseUrl)
    }

    /**
     * 功能: 根据 baseUrl 创建的接口
     *
     * @param clazz 接口名
     */
    fun <T> create(clazz: Class<T>): T {
        return defaultRetrofit.create(clazz)
    }

    /**
     * 功能: 根据 baseUrl 创建的接口
     *
     * @param baseUrl
     * @param clazz 接口名
     */
    fun <T> createByNew(baseUrl: String, clazz: Class<T>): T {
        return buildRetrofit(baseUrl).create(clazz)
    }

    /**
     * 功能: 实现多BaseUrl, 静态或者动态设置Header
     *
     * @param baseUrls key为baseUrl的name, value为baseUrl
     * @param clazz 接口类
     * @sample CallFactoryProxy
     */
    fun <T> createByMultiUrls(baseUrls: HashMap<String, String>, clazz: Class<T>): T {
        if (baseUrls.isEmpty()) {
            throw IllegalArgumentException("urls can not be empty")
        }
        return buildRetrofit(baseUrls).create(clazz)
    }

    inline fun <reified T> create(): T = create(T::class.java)

    inline fun <reified T> createByNew(baseUrl: String): T = createByNew(baseUrl, T::class.java)

    inline fun <reified T> createByMultiUrls(urls: HashMap<String, String>): T =
        createByMultiUrls(urls, T::class.java)

    /**
     * 功能: 构建Retrofit对象，需要根据具体的需求更改
     *
     * @param baseUrl
     *
     * @return Retrofit对象
     *
     */
    private fun buildRetrofit(baseUrl: String) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder()
                .connectTimeout(Config.CONNECT_TIME_OUT, Config.TIME_UNIT)
                .readTimeout(Config.READ_TIME_OUT, Config.TIME_UNIT)
                .writeTimeout(Config.WRITE_TIME_OUT, Config.TIME_UNIT)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    // 打印Log的等级
                    level = HttpLoggingInterceptor.Level.BASIC
                })
                .build()
        )
        .build()


    /**
     * 功能: 构建Retrofit对象, 可设置多个baseUrl
     *
     * @param baseUrls
     *
     * @return Retrofit对象
     *
     */
    private fun buildRetrofit(baseUrls: HashMap<String, String>): Retrofit {
        //  获取第一个作为 baseUrl
        val baseUrl = baseUrls.entries.iterator().next().value
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(Config.CONNECT_TIME_OUT, Config.TIME_UNIT)
            .readTimeout(Config.READ_TIME_OUT, Config.TIME_UNIT)
            .writeTimeout(Config.WRITE_TIME_OUT, Config.TIME_UNIT)
            .addInterceptor(HttpLoggingInterceptor().apply {
                // 打印Log的等级
                level = HttpLoggingInterceptor.Level.BASIC
            })
            .build()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            // 使用RxJava时需要加上
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .callFactory(object : CallFactoryProxy(okHttpClient) {
                override fun getNewUrl(baseUrlName: String, request: Request): String? {
                    baseUrls[baseUrlName]?.let {
                        val newUrl = request.url.toString().replace(baseUrl, it)
                        Log.v(TAG, "newUrl: $newUrl")
                        return newUrl
                    }
                    return null
                }
            })
            .build()
    }

    object Config {
        // 时间单位
        val TIME_UNIT = TimeUnit.SECONDS

        // 连接超时时长
        const val CONNECT_TIME_OUT = 10L

        // 读取超时时长
        const val READ_TIME_OUT = 10L

        // 写入超时时长
        const val WRITE_TIME_OUT = 10L

        // 默认baseUrl
        const val defaultBaseUrl: String = "https://xxx.xxx.xxx"
    }

}
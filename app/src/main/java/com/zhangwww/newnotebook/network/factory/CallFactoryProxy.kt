package com.zhangwww.newnotebook.network.factory

import android.util.Log
import okhttp3.Call
import okhttp3.Request

/**
 * 功能描述：代理{@link okhttp3.Call.Factory} 拦截{@link #newCall(Request)}方法
 * 参考：https://blog.csdn.net/issingleman/article/details/100542499
 */
abstract class CallFactoryProxy(private val delegate: Call.Factory) : Call.Factory {

    override fun newCall(request: Request): Call {
        val baseUrlName = request.header(NAME_BASE_URL)
        if (baseUrlName != null) {
            val newHttpUrl = getNewUrl(baseUrlName, request)
            if (newHttpUrl != null) {
                val newRequest = request.newBuilder().url(newHttpUrl).build()
                return delegate.newCall(newRequest)
            } else {
                Log.w(TAG, "getNewUrl() return null when baseUrlName=$baseUrlName")
            }
        }
        return delegate.newCall(request)
    }

    protected abstract fun getNewUrl(
        baseUrlName: String,
        request: Request
    ): String?

    companion object {
        private const val NAME_BASE_URL = "BaseUrlName"
        private const val TAG = "CallFactoryProxy"
    }

    /**
     * example
     * 静态替换
    @FormUrlEncoded
    @Headers("BaseUrlName:xxx")//
    @POST("user/login")
    Call<LoginInfo> getLogin(@Field("username") String username, @Field("password") String password);

    * 动态替换
    @FormUrlEncoded
    @POST("user/login")
    Call<LoginInfo> getLogin(@Header("BaseUrlName") String baseUrlName, @Field("username") String username, @Field("password") String password);
     */

}
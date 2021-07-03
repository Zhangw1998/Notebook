package com.zhangwww.newnotebook.network.api

import com.blankj.utilcode.util.EncryptUtils
import okhttp3.Interceptor
import okhttp3.Response
import kotlin.random.Random

class BmobInterceptor : Interceptor {

    /**
     * X-Bmob-SDK-Type	string	SDK类型，这里固定API
     * X-Bmob-Safe-Timestamp	int	客户端请求的 unix 时间戳（UTC），精确到毫秒
     * X-Bmob-Noncestr-Key	string	客户端请求产生的一个随机码，长度16个字符
     * X-Bmob-Secret-Key	string	Bmob控制台应用密匙 Secret Key
     * X-Bmob-Safe-Sign	string	md5 签名，签名规则 md5(url + timeStamp + safeToken + noncestr)
     */

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url.toString()
        val timeStamp = System.currentTimeMillis()
        val nonceString = createNonceString()
        val start = BombClient.BASE_URL.length
        var end = originalUrl.indexOf("?")
        if (end == -1) {
            end = originalUrl.length
        }
        val url = originalUrl.substring(start, end)
        val sign = url + timeStamp + "zhangw" + nonceString
        val safeSign = EncryptUtils.encryptMD5ToString(sign).lowercase()

        val builder = originalRequest
            .newBuilder()
            .url(originalRequest.url)
            .removeHeader("Content-Type")
            .addHeader("content-type", "application/json")
            .addHeader("X-Bmob-SDK-Type", "API")
            .addHeader("X-Bmob-Safe-Sign", safeSign)
            .addHeader("X-Bmob-Safe-Timestamp", timeStamp.toString())
            .addHeader("X-Bmob-Noncestr-Key", nonceString)
            .addHeader("X-Bmob-Secret-Key", BombClient.secretKey)
        var response = chain.proceed(builder.build())
        response = response.newBuilder()
            .request(originalRequest)
            .build()
        return response
    }

    /**
     *
     */


     /**
      * @description: 创建无序的字符串
      * @date: 2021/7/3 10:09
      * @author: Zhangwww
      * @param length 字符串的长度
      * @return 无序的字符串
      */
    private fun createNonceString(length: Int = 16): String {
        val string = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        val stringBuilder = StringBuilder()
        for (i in 0 until length) {
            stringBuilder.append(string[Random.nextInt(string.length)])
        }
        return stringBuilder.toString()
    }


}
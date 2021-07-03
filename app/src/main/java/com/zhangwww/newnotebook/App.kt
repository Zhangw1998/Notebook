package com.zhangwww.newnotebook

import android.app.Application
import android.content.Context
import com.zhangwww.newnotebook.network.api.BombClient
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        initAppConfig()
    }

     /**
      * @description: 初始化app的一些配置信息
      * @date: 2021/7/3 11:08
      * @author: Zhangwww
      */
    private fun initAppConfig() {
         BombClient.secretKey = applicationContext.resources.getString(R.string.bmob_secret_key)
    }

    companion object {
        lateinit var appContext: Context
            private set
    }

}
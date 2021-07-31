package com.zhangwww.newnotebook.logger

import android.util.Log
import com.blankj.utilcode.util.AppUtils


object Logger {

    const val logName = "Logger"

    val isDebug = AppUtils.isAppDebug()

}

fun log(tag: String = Logger.logName, msg: String) {
    if (Logger.isDebug) {
        Log.d(tag, msg)
    }
}

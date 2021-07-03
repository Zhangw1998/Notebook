package com.zhangwww.newnotebook.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


fun Activity.goSettingActivity() {
    val intent = Intent().apply {
        this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        this.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
        this.data = Uri.fromParts("package", application.packageName, null)
    }
    startActivity(intent)
}

inline fun <reified T : AppCompatActivity> AppCompatActivity.launchActivity(context: Context) {
    context.startActivity(Intent(context, T::class.java))
}
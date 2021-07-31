package com.zhangwww.newnotebook.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zhangwww.newnotebook.R


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

fun Context.getCompatColor(@ColorRes id: Int) : Int {
    return resources.getCompatColor(id)
}

fun Resources.getCompatColor(@ColorRes id: Int) : Int {
    return ResourcesCompat.getColor(this, id, null)
}
package com.zhangwww.newnotebook.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T : AppCompatActivity> Fragment.launchActivity(context: Context) {
    context.startActivity(Intent(context, T::class.java))
}

fun Fragment.goSettingActivity() {
    val intent = Intent().apply {
        this.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        this.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
        this.data = Uri.fromParts("package", requireActivity().application.packageName, null)
    }
    startActivity(intent)
}
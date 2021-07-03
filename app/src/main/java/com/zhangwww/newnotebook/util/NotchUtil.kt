package com.zhangwww.newnotebook.util

import android.app.Activity
import android.os.Build
import android.view.Window
import android.view.WindowManager

fun Activity.useNotchArea(use: Boolean = true) {
    if (Build.VERSION.SDK_INT >= 28) {
        val lp = window.attributes
        if (use) {
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        } else {
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER
        }
        window.attributes = lp
    }
}
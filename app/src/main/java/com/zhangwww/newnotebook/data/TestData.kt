package com.zhangwww.newnotebook.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TestData(
    var data: String,
    var length: Int
) : Parcelable
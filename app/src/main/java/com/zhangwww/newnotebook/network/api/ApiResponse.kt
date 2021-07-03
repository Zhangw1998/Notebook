package com.zhangwww.newnotebook.network.api

import java.io.Serializable

data class ApiResponse<T>(
    val data: T,
    val code: Int,
    val msg: String?
): Serializable {

    fun isSuccess(): Boolean = code in IntRange(200, 299)

}
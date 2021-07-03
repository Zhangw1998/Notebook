package com.zhangwww.newnotebook.network.models

data class ApiResponse<T>(
    var data: T,
    var msg: String = "success",
    var code: Int = 200
) {

    // 需要根据实际情况修改条件
    fun isSuccessful() = code == 200

}
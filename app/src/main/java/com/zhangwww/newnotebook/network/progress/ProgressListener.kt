package com.zhangwww.networkmodule.process

interface ProgressListener {
    fun update(bytesRead: Long, contentLength: Long, done: Boolean)
}

/**
 * 参数说明
 * bytesRead        已下载byte数
 * contentLength    总byte数
 * done             下载是否完成
 */
typealias DownloadListener = (Long, Long, Boolean) -> Unit

/**
 * 参数说明
 * bytesWrite       已上传byte数
 * contentLength    总byte数
 * done             下载是否完成
 */
typealias UploadListener = (Long, Long, Boolean) -> Unit
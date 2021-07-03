package com.zhangwww.networkmodule.process

import okhttp3.MediaType
import okhttp3.RequestBody
import okio.*

class ProgressRequestBody(private val requestBody: RequestBody, val progressListener: ProgressListener) : RequestBody() {

    private var bufferedSink: BufferedSink? = null

    override fun contentType(): MediaType? {
        return requestBody.contentType()
    }

    override fun contentLength(): Long {
        return requestBody.contentLength()
    }

    override fun writeTo(sink: BufferedSink) {
        if (bufferedSink == null) {
            bufferedSink = sink(sink).buffer()
        }
        bufferedSink?.let {
            requestBody.writeTo(it)
            it.flush()
        }
    }

    private fun sink(sink: Sink): Sink {
        return object : ForwardingSink(sink) {
            var byteWrite = 0L
            var contentLength = 0L
            override fun write(source: Buffer, byteCount: Long) {
                super.write(source, byteCount)
                if (contentLength == 0L) {
                    contentLength = contentLength()
                }
                byteWrite += byteCount
                progressListener.update(byteWrite, contentLength, byteCount == contentLength)
            }
        }
    }
}
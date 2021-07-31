package com.zhangwww.newnotebook.network.api

import com.google.gson.Gson
import com.google.gson.JsonIOException
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonToken
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import okio.Buffer
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.IOException
import java.io.OutputStreamWriter
import java.io.Writer
import java.lang.reflect.Type
import java.nio.charset.Charset

class BmobConverterFactory : Converter.Factory() {

    private val gson = Gson()

    companion object {
        @JvmStatic
        fun create(): Converter.Factory {
            return BmobConverterFactory()
        }
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return BombResponseBodyConverter(gson, adapter)
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<Annotation>,
        methodAnnotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody> {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return BmobRequestBodyConverter(gson, adapter)
    }
}

internal class BmobRequestBodyConverter<T>(
    private val gson: Gson,
    private val adapter: TypeAdapter<T>
) : Converter<T, RequestBody> {

    @Throws(IOException::class)
    override fun convert(value: T): RequestBody {
        val buffer = Buffer()
        val writer: Writer = OutputStreamWriter(buffer.outputStream(), UTF_8)
        val jsonWriter = gson.newJsonWriter(writer)
        adapter.write(jsonWriter, value)
        jsonWriter.close()
        return buffer.readByteString().toRequestBody(MEDIA_TYPE)
    }

    companion object {
        private val MEDIA_TYPE: MediaType = "application/json; charset=UTF-8".toMediaType()
        private val UTF_8 = Charset.forName("UTF-8")
    }
}

internal class BombResponseBodyConverter<T>(
    private val gson: Gson,
    private val adapter: TypeAdapter<T>
) : Converter<ResponseBody, T> {
    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T {
        val jsonReader = gson.newJsonReader(value.charStream())
        value.use {
            val result = adapter.read(jsonReader)
            if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
                throw JsonIOException("JSON document was not fully consumed.")
            }
            return result
        }
    }
}

/**
 * POST请求成功:
 * status: 201 Created
 * body:
 * {"createdAt": "2011-08-20 02:06:57","objectId": "e1kXT22L"}
 *
 * PUT 请求成功:
 * status: 200 OK
 * body:
 * {"updatedAt": "YYYY-mm-dd HH:ii:ss"}
 *
 * DELETE 请求成功:
 * status:200 OK
 * body:
 * {"msg": "ok"}
 * */
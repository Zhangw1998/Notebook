package com.zhangwww.newnotebook.network.api

import com.zhangwww.newnotebook.data.TestData
import retrofit2.http.*

interface BombApi {

    @GET("/1/classes/TestData/{objectId}")
    suspend fun getTest(@Path("objectId") objectId: String): Any

    @POST("/1/classes/TestData/")
    suspend fun postTest(@Body testData: TestData): Any

    @PUT("/1/classes/TestData/{objectId}")
    suspend fun updateTest(@Path("objectId") objectId: String, @Body testData: TestData): Any

    @DELETE("/1/classes/TestData/{objectId}")
    suspend fun deleteTest(@Path("objectId") objectId: String): Any

}
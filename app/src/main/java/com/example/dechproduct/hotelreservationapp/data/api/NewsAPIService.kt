package com.example.dechproduct.hotelreservationapp.data.api

import com.example.dechproduct.hotelreservationapp.BuildConfig
import com.example.dechproduct.hotelreservationapp.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {
    // suspend fun ใส่เพราะใช้ coroutine with retrofit

    @GET("v2/top-headlines")
    suspend fun getTopsHeadlines(
        @Query("country")
        country: String,
        @Query("page")
        page: Int,
        @Query("apiKey")
        apiKey:String = BuildConfig.API_KEY
    ):Response<APIResponse>


}
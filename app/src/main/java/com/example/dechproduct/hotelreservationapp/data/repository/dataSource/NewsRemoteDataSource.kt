package com.example.dechproduct.hotelreservationapp.data.repository.dataSource

import android.app.DownloadManager
import com.example.dechproduct.hotelreservationapp.data.model.APIResponse
import retrofit2.Response



interface NewsRemoteDataSource {
    suspend fun getTopHeadlines(country : String, page : Int):Response<APIResponse> //return type เป็น response ของ type APIResponse

    suspend fun getSearchedNews(country : String,searchQuery: String, page : Int):Response<APIResponse> //return type เป็น response ของ type APIResponse

}
package com.example.dechproduct.hotelreservationapp.data.repository.dataSourceImpl

import com.example.dechproduct.hotelreservationapp.data.api.NewsAPIService
import com.example.dechproduct.hotelreservationapp.data.model.APIResponse
import com.example.dechproduct.hotelreservationapp.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response
import java.nio.channels.spi.AbstractSelectionKey

class NewsRemoteDataSourceImpl(
    private val newsAPIService: NewsAPIService
):NewsRemoteDataSource {
    override suspend fun getTopHeadlines(country : String, page : Int): Response<APIResponse> {
        return newsAPIService.getTopHeadlines(country,page)
    }

    override suspend fun getSearchedNews(
        country: String,
        searchQuery: String,
        page: Int
    ): Response<APIResponse> {
        return newsAPIService.getSearchedTopHeadlines(country, searchQuery, page)
    }
}
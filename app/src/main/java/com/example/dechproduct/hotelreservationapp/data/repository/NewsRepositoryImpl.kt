package com.example.dechproduct.hotelreservationapp.data.repository

import com.example.dechproduct.hotelreservationapp.data.model.APIResponse
import com.example.dechproduct.hotelreservationapp.data.model.Article
import com.example.dechproduct.hotelreservationapp.data.repository.dataSource.NewsRemoteDataSource
import com.example.dechproduct.hotelreservationapp.data.util.Resource
import com.example.dechproduct.hotelreservationapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
//
//class NewsRepositoryImpl(
//    private val newsRemoteDataSource: NewsRemoteDataSource
//) : NewsRepository {
//
//    private fun responseResource(response: Response<APIResponse>): Resource<APIResponse> {
//        if (response.isSuccessful) {
//            response.body()?.let { result ->  // ต้องการเก็บ body ของ response และ return มันว่า success or fail
//                    return Resource.Success(result)
//                }
//        }
//        return Resource.Error(response.message())
//    }
//
//    override suspend fun getNewsHeadlines(country: String, page : Int): Resource<APIResponse> {
//        return responseResource(newsRemoteDataSource.getTopHeadLines(country, page)) // (newsRemoteDataSource.getTopHeadLines() มันจะ return response object เรา convert ให้เป็น resource object
//    }
//
//    override suspend fun getSearchedNews(searchQuery: String): Resource<APIResponse> {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun saveNews(article: Article) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun deleteNews(article: Article) {
//        TODO("Not yet implemented")
//    }
//
//    override fun getSavedNews(): Flow<List<Article>> {
//        TODO("Not yet implemented")
//    }
//}


class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource
):NewsRepository {
    override suspend fun getNewsHeadlines(country : String, page : Int): Resource<APIResponse> {
        return responseToResource(newsRemoteDataSource.getTopHeadlines(country,page))
    }

    private fun responseToResource(response:Response<APIResponse>):Resource<APIResponse>{
        if(response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }


    override suspend fun getSearchedNews(searchQuery: String): Resource<APIResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun saveNews(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNews(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }
}
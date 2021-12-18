package com.example.dechproduct.hotelreservationapp.domain.repository

import com.example.dechproduct.hotelreservationapp.data.model.APIResponse
import com.example.dechproduct.hotelreservationapp.data.model.Article
import com.example.dechproduct.hotelreservationapp.util.NewsResource
import kotlinx.coroutines.flow.Flow

//
//interface NewsRepository {
//
//    //network communication
//    suspend fun getNewsHeadlines(country: String,page: Int): Resource<APIResponse> //ไป implement ใน NewsRepositoryImpl class
//    suspend fun getSearchedNews(searchQuery:String): Resource<APIResponse> // search รับ string สำหรับ Query
//
//    //function related to local DB -> save, get , delete data from local db
//
//    // save data to db -> add instance of object as parameter
//    suspend fun saveNews(article:Article)
//
//    // delete data to db
//    suspend fun deleteNews(article: Article)
//
//    //get Data from db -> return เป็น list ของ object แต่ว่าอยากได้แบบ real time -> get Notified ทุกๆ change ที่เกิดขึ้น ใน article table เลยใช้ livedata?
//    // -> ใช้ LiveData ไม่เหมาะ กับ repository เพราะควรทำใน view model
//    // ใช้ kotlin coroutine flow แทนเพราะดีกว่า -> แต่ก่อนเป็น Rxjava ที่ใช้ getData จาก repository ไปที่ view model และใข้ livedata สำหรับ emit data นั้นจาก viewModel ส่งไป activity or fragment
//    // -> ซึ่งใข้ kotlin flow api สำหรับ handle stream of data asynchronously
//    // => อีกทั้ง Room library ยังรองรับการ get the data as "flow" -> เปลี่ยนจาก LiveData<List<Article>> -> Flow<List<Article>> -> ไป add dependency coroutine ก่อน
//    // เราจะ get list ของ data จาก database tabิle as a flow
//    // -> ใน view model class เราจะเก็บ stream of data และ emit มันเป็น livedata
//    //ดังนั้น function นี้เลยจะ return เป็น data stream -> เลยไม่ต้องใช้เป็น suspending function (เพราะไม่ต้องการ pause or resume data ทีหลัง)
//     fun getSavedNews(): Flow<List<Article>>
//
//    //end repository interface => ทำ usecase class ต่อ
//
//}

interface NewsRepository{

    suspend fun getNewsHeadlines(country : String, page : Int): NewsResource<APIResponse>
    suspend fun getSearchedNews(country: String,searchQuery:String, page: Int) : NewsResource<APIResponse>
    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    fun getSavedNews(): Flow<List<Article>>




}
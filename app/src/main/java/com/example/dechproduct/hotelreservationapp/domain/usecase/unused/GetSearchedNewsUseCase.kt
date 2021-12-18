package com.example.dechproduct.hotelreservationapp.domain.usecase.unused

import com.example.dechproduct.hotelreservationapp.data.model.APIResponse
import com.example.dechproduct.hotelreservationapp.util.NewsResource
import com.example.dechproduct.hotelreservationapp.domain.repository.NewsRepository

// Use case สำหรับ Search News
//Domain Layer for get use case ซึ่ง require repository -> สร้าง repository แล้ว pass มาเป็น constructor ใน use case class ทำแบบเดืม
class GetSearchedNewsUseCase (private val newsRepository: NewsRepository){
    suspend fun execute(country: String,searchQuery:String, page:Int): NewsResource<APIResponse> {
    return newsRepository.getSearchedNews(country,searchQuery, page)
    }
}
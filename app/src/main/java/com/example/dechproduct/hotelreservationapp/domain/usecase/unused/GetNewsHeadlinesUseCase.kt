package com.example.dechproduct.hotelreservationapp.domain.usecase.unused

import com.example.dechproduct.hotelreservationapp.data.model.APIResponse
import com.example.dechproduct.hotelreservationapp.util.NewsResource
import com.example.dechproduct.hotelreservationapp.domain.repository.NewsRepository

// ตย Use case สำหรับ get Data จาก api
//Domain Layer for get use case ซึ่ง require repository -> สร้าง repository แล้ว pass มาเป็น constructor ใน use case class

class GetNewHeadlinesUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(country : String, page : Int): NewsResource<APIResponse> {
        return newsRepository.getNewsHeadlines(country,page)
    }
}
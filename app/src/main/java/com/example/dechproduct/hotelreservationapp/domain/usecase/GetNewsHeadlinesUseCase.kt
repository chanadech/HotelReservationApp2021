package com.example.dechproduct.hotelreservationapp.domain.usecase

import com.example.dechproduct.hotelreservationapp.data.model.APIResponse
import com.example.dechproduct.hotelreservationapp.data.util.Resource
import com.example.dechproduct.hotelreservationapp.domain.repository.NewsRepository

// ตย Use case สำหรับ get Data จาก api
//Domain Layer for get use case ซึ่ง require repository -> สร้าง repository แล้ว pass มาเป็น constructor ใน use case class

class GetNewHeadlinesUseCase(private val newsRepository: NewsRepository) {

    // สร้าง execute function มี return type เป็น resource instance of type ApiResponse -> อ้างอิงจาก repository
    // อาจจะ get some data จ่าก repo แล้วมา modify และ return type อื่นออกไปก็ได้
    //แต่ในครั้งนี้เราแค่ต้องการให้้มัน return data ทืี่ taken from repository เฉยๆ
    suspend fun execute(country: String, page: Int): Resource<APIResponse> {
        return newsRepository.getNewsHeadlines(country,page)
    }
}
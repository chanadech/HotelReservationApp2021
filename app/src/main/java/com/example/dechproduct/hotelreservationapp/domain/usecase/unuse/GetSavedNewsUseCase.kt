package com.example.dechproduct.hotelreservationapp.domain.usecase.unuse

import com.example.dechproduct.hotelreservationapp.data.model.Article
import com.example.dechproduct.hotelreservationapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

// Use case สำหรับให้ user view สิ่งที่ save ไว้
//Domain Layer for get use case ซึ่ง require repository -> สร้าง repository แล้ว pass มาเป็น constructor ใน use case class ทำแบบเดืม

class GetSavedNewsUseCase(private val newsRepository: NewsRepository){
     fun  execute(): Flow<List<Article>> {
         return newsRepository.getSavedNews()
     }
}
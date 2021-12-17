package com.example.dechproduct.hotelreservationapp.domain.usecase.unuse

import com.example.dechproduct.hotelreservationapp.data.model.Article
import com.example.dechproduct.hotelreservationapp.domain.repository.NewsRepository

// Usecase สำหรับ save data
//Domain Layer for get use case ซึ่ง require repository -> สร้าง repository แล้ว pass มาเป็น constructor ใน use case class ทำแบบเดืม

class SaveNewsUseCase (private val newsRepository: NewsRepository){
    suspend fun execute(article: Article) = newsRepository.saveNews(article) // ไม่ได้ return อะไร ทำเป็น single line ได้
}
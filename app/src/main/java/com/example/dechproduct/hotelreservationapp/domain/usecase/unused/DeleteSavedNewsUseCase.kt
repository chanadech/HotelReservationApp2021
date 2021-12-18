package com.example.dechproduct.hotelreservationapp.domain.usecase.unused

import com.example.dechproduct.hotelreservationapp.data.model.Article
import com.example.dechproduct.hotelreservationapp.domain.repository.NewsRepository

//ี๊Usecase สำหรับ delete สิ่งที่ save ไว้
class DeleteSavedNewsUseCase (private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article) = newsRepository.deleteNews(article) // single line
}
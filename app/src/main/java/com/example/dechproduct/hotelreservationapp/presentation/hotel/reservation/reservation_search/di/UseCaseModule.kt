package com.example.dechproduct.hotelreservationapp.presentation.hotel.reservation.reservation_search.di

import com.example.dechproduct.hotelreservationapp.domain.repository.NewsRepository
import com.example.dechproduct.hotelreservationapp.domain.usecase.GetNewHeadlinesUseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.GetSearchedNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideNewsHeadLinesUseCase(
        newsRepository: NewsRepository
    ):GetNewHeadlinesUseCase {
        return GetNewHeadlinesUseCase(newsRepository)
    }
}
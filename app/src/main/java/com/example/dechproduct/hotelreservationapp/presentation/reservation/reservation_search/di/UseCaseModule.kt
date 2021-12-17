package com.example.dechproduct.hotelreservationapp.presentation.reservation.reservation_search.di

import com.example.dechproduct.hotelreservationapp.domain.repository.NewsRepository
import com.example.dechproduct.hotelreservationapp.domain.usecase.unuse.GetNewHeadlinesUseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.unuse.GetSearchedNewsUseCase
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
    fun provideGetNewsHeadLinesUseCase(
        newsRepository: NewsRepository
    ): GetNewHeadlinesUseCase {
        return GetNewHeadlinesUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun provideGetSearchedNewsUseCase(
        newsRepository: NewsRepository
    ): GetSearchedNewsUseCase {
        return GetSearchedNewsUseCase(newsRepository)
    }
}
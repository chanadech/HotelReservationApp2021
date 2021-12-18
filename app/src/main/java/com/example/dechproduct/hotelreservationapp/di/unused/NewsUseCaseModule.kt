package com.example.dechproduct.hotelreservationapp.di.unused

import com.example.dechproduct.hotelreservationapp.domain.repository.NewsRepository
import com.example.dechproduct.hotelreservationapp.domain.usecase.unused.GetNewHeadlinesUseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.unused.GetSearchedNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NewsUseCaseModule {

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
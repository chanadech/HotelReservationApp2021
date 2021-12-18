package com.example.dechproduct.hotelreservationapp.di.unused

import com.example.dechproduct.hotelreservationapp.data.repository.NewsRepositoryImpl
import com.example.dechproduct.hotelreservationapp.data.repository.dataSource.NewsRemoteDataSource
import com.example.dechproduct.hotelreservationapp.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NewsRepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource
    ):NewsRepository{
        return NewsRepositoryImpl(newsRemoteDataSource)
    }
}
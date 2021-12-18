package com.example.dechproduct.hotelreservationapp.di.unused

import com.example.dechproduct.hotelreservationapp.data.api.NewsAPIService
import com.example.dechproduct.hotelreservationapp.data.repository.dataSource.NewsRemoteDataSource
import com.example.dechproduct.hotelreservationapp.data.repository.dataSourceImpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(newsAPIService: NewsAPIService): NewsRemoteDataSource{
        return NewsRemoteDataSourceImpl(newsAPIService)
    }
}
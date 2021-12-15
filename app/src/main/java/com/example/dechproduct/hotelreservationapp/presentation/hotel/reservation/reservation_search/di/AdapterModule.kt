package com.example.dechproduct.hotelreservationapp.presentation.hotel.reservation.reservation_search.di

import com.example.dechproduct.hotelreservationapp.presentation.hotel.reservation.reservation_search.adapter.NewsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {
    @Singleton
    @Provides
    fun provideNewsAdapter():NewsAdapter{
        return NewsAdapter()
    }
}
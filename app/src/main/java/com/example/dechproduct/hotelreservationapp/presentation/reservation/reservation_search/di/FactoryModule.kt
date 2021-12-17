package com.example.dechproduct.hotelreservationapp.presentation.reservation.reservation_search.di

import android.app.Application
import com.example.dechproduct.hotelreservationapp.domain.usecase.unuse.GetNewHeadlinesUseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.unuse.GetSearchedNewsUseCase
import com.example.dechproduct.hotelreservationapp.presentation.reservation.reservation_search.SearchReservationViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideNewViewModelFactory(
        application: Application,
        getNewsHeadlinesUseCase: GetNewHeadlinesUseCase,
        getSearchedNewsUseCase: GetSearchedNewsUseCase
    ): SearchReservationViewModelFactory {
        return SearchReservationViewModelFactory(
            application,
            getNewsHeadlinesUseCase,
            getSearchedNewsUseCase
        )
    }
}
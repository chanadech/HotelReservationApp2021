package com.example.dechproduct.hotelreservationapp.di.unused

import android.app.Application
import com.example.dechproduct.hotelreservationapp.domain.usecase.unused.GetNewHeadlinesUseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.unused.GetSearchedNewsUseCase
import com.example.dechproduct.hotelreservationapp.presentation.reservation.search.SearchReservationViewModelFactory
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
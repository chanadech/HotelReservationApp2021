package com.example.dechproduct.hotelreservationapp.di

import com.example.dechproduct.hotelreservationapp.domain.repository.ReservationRepository
import com.example.dechproduct.hotelreservationapp.domain.repository.UserRepository
import com.example.dechproduct.hotelreservationapp.domain.usecase.UseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.login.LoginUseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.reservation.AddReserveUseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.reservation.EditReserveUseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.reservation.RemoveReserveUseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.reservation.SearchReserveUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideUseCase(userRepository: UserRepository , reservationRepository: ReservationRepository): UseCase {
        return UseCase(
            LoginUseCase(userRepository),

            AddReserveUseCase(reservationRepository),
            SearchReserveUseCase(reservationRepository),
            EditReserveUseCase(reservationRepository),
            RemoveReserveUseCase(reservationRepository),
        )
    }
}
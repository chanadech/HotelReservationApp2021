package com.example.dechproduct.hotelreservationapp.di

import android.content.Context
import android.content.SharedPreferences
import com.example.dechproduct.hotelreservationapp.data.repository.ReservationRepositoryImpl
import com.example.dechproduct.hotelreservationapp.data.repository.UserRepositoryImpl
import com.example.dechproduct.hotelreservationapp.domain.repository.ReservationRepository
import com.example.dechproduct.hotelreservationapp.domain.repository.UserRepository
import com.example.dechproduct.hotelreservationapp.domain.usecase.UseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.login.LoginUseCase
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        databaseReference: FirebaseDatabase,
        sharedPreferences: SharedPreferences,
    ): UserRepository {
        return UserRepositoryImpl(databaseReference, sharedPreferences)
    }
    
    @Singleton
    @Provides
    fun provideReservationRepository(
        databaseReference: FirebaseDatabase,
        sharedPreferences: SharedPreferences,
    ): ReservationRepository {
        return ReservationRepositoryImpl(databaseReference, sharedPreferences)
    }

}
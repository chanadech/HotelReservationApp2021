package com.example.dechproduct.hotelreservationapp.di

import android.content.Context
import android.content.SharedPreferences
import com.example.dechproduct.hotelreservationapp.data.repository.UserRepositoryImpl
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
object LoginModule {

    @Provides
    @Singleton
    fun provideRealTimeDb(): FirebaseDatabase {
        return FirebaseDatabase.getInstance(Constants.FIREBASE_DB_URL)
    }

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideUserRepo(
        databaseReference: FirebaseDatabase,
        sharedPreferences: SharedPreferences,
    ): UserRepository {
        return UserRepositoryImpl(databaseReference, sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideUseCases(userRepository: UserRepository): UseCase {
        return UseCase(
            LoginUseCase(userRepository),
        )
    }
}
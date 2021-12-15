package com.example.dechproduct.hotelreservationapp.data.repository

import android.content.SharedPreferences
import com.example.dechproduct.hotelreservationapp.data.model.User
import com.example.dechproduct.hotelreservationapp.data.util.Resource
import com.example.dechproduct.hotelreservationapp.domain.repository.UserRepository
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    val sharedPreferences: SharedPreferences): UserRepository{

    override suspend fun login(username: String, password: String): Resource<User> {
        TODO("Not yet implemented")
    }

}
package com.example.dechproduct.hotelreservationapp.domain.repository

import com.example.dechproduct.hotelreservationapp.data.model.User
import com.example.dechproduct.hotelreservationapp.util.Resource

interface UserRepository {
    suspend fun login(username: String, password: String):Resource<User>
}
package com.example.dechproduct.hotelreservationapp.util

//use form google doc jetpack guide
sealed class NewsResource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : NewsResource<T>(data)
    class Loading<T>(data: T? = null) : NewsResource<T>(data)
    class Error<T>(message: String, data: T? = null) : NewsResource<T>(data, message)
}



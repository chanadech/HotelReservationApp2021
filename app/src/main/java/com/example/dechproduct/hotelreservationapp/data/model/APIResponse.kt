package com.example.dechproduct.hotelreservationapp.data.model


import com.google.gson.annotations.SerializedName

data class APIResponse(
    @SerializedName("Foo1")
    val articles: List<String>,
    @SerializedName("Foo2")
    val status: String,
    @SerializedName("Foo3")
    val totalResults: Int
)
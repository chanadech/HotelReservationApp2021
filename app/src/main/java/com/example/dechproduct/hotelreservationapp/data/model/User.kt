package com.example.dechproduct.hotelreservationapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(

    var userID: String?,

    var userName: String?,

    var userDisplayName: String?,

    var password: String = "",

    var userType: Int = 0,

    var roomID: String = "") : Parcelable {
}
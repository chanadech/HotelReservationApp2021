package com.example.dechproduct.hotelreservationapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (

    @PrimaryKey(autoGenerate = true)
    var userId: Int = -1,

    @ColumnInfo(name = "user_name")
    var userName: String,

    @ColumnInfo(name = "display_name")
    var userDisplayName: String,

    @ColumnInfo(name = "pass_text")
    var password: String,

    @ColumnInfo(name = "user_type")
    var userType: Int,

    @ColumnInfo(name = "room_id")
    var roomID: String){
}
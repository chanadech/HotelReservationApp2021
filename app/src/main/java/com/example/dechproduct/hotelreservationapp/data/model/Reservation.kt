package com.example.dechproduct.hotelreservationapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Reservation (

    @PrimaryKey(autoGenerate = true)
    var bookingId: Int = -1,

    @ColumnInfo(name = "first_name")
    var firstName: String,

    @ColumnInfo(name = "last_name")
    var lastName: String,

    @ColumnInfo(name = "phone_no")
    var phoneNumber: String,

    @ColumnInfo(name = "payment_type")
    var paymentType: String,

    @ColumnInfo(name = "reserve_date")
    var reserveDate: ReserveDate,

    @ColumnInfo(name = "passport_no")
    var passportID: String,

    @ColumnInfo(name = "ssn_no")
    var ssnID: String,

    @ColumnInfo(name = "address")
    var Address: Address

    ){
}
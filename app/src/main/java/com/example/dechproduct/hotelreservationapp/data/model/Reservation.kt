package com.example.dechproduct.hotelreservationapp.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.util.*

@Parcelize
data class Reservation (

    var bookingId: Int = -1,

    var firstName: String = "",

    var lastName: String = "",

    var phoneNumber: String = "",

    var paymentType: String = "",

    //TODO: Implements ReserveDate object
    var reserveDate: String = "",

    var passportID: String = "",

    var ssnID: String = "",

    //TODO: Implements Address object
    var address: String = ""

    ) : Parcelable {
}
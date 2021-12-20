package com.example.dechproduct.hotelreservationapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Reservation(

    var bookingID: Int? = -1,

    var firstName: String? = "",

    var lastName: String? = "",

    var phoneNumber: String? = "",

    var paymentType: String? = "",

    //TODO: Implements ReserveDate object
    var reserveDate: String? = "",

    var passportID: String? = "",

    var ssnID: String? = "",

    //TODO: Implements Address object
    var address: String? = ""

    ) : Parcelable {
}
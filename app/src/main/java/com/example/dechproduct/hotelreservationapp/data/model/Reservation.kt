package com.example.dechproduct.hotelreservationapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Reservation(

    var bookingID: String? = "",

    var firstName: String? = "",

    var lastName: String? = "",

    var phoneNumber: String? = "",

    var paymentType: String? = "",

    //TODO: Implements ReserveDate object
    var reserveDateIn: String? = "",

    var reserveDateOut: String? = "",

    var passportID: String? = "",

    var ssnID: String? = "",

    //TODO: Implements Address object
    var address: String? = ""

    ) : Parcelable {
}
package com.example.dechproduct.hotelreservationapp.util

object Constants {
    //Room Model
    const val ROOM_TYP_STANDARD = "standard"
    const val ROOM_TYP_DELUXE = "deluxe"
    const val ROOM_TYP_SUITE = "suite"

    const val ROOM_BED_SINGLE = "single"
    const val ROOM_BED_DOUBLE = "twin"

    const val ROOM_LOC_NORMAL = "standard"
    const val ROOM_LOC_CORNER = "corner"

    const val ROOM_MAX_CAPACITY = 2

    //Database
    const val FIREBASE_DB_URL =
        "https://nestling-pholder-user-db-default-rtdb.asia-southeast1.firebasedatabase.app/"

    const val USER_DB_NODE = "user"
    const val USER_KEY_PASSWORD = "password"
    const val USER_KEY_ID = "id"
    const val USER_KEY_NAME = "name"
    const val USER_KEY_USERNAME = "username"

    const val BOOK_DB_NODE = "booking"
    const val BOOK_KEY_FNAME = "firstName"
    const val BOOK_KEY_LNAME = "lastName"
    const val BOOK_KEY_ID = "bookingID"
    const val BOOK_KEY_PHONE = "phoneNumber"
    const val BOOK_KEY_PAYMENT = "paymentType"
    const val BOOK_KEY_DATE = "reserveDate"
    const val BOOK_KEY_PASSPORT = "passportID"
    const val BOOK_KEY_SSN = "ssnID"
    const val BOOK_KEY_ADDRESS = "address"

    //Persistent Data
    const val SHARED_PREF_NAME = "USER_SESSION"

    //Miscellaneous
    const val LOGGED_IN_USER_NAME = "LOGGED_IN_USER"
    const val LOGGED_IN_DISPLAY_NAME = "LOGGED_IN_USER_NAME"
}
package com.example.dechproduct.hotelreservationapp.util

object Constants {
    //Database
    const val FIREBASE_DB_URL =
        "https://nestling-pholder-user-db-default-rtdb.asia-southeast1.firebasedatabase.app/"

    const val USER_DB_NODE = "user"
    const val USER_KEY_PASSWORD = "password"
    const val USER_KEY_ID = "id"
    const val USER_KEY_NAME = "name"
    const val USER_KEY_USERNAME = "username"

    const val BOOK_DB_NODE = "booking"
    const val BOOK_KEY_FNAME = "first_name"
    const val BOOK_KEY_LNAME = "last_name"
    const val BOOK_KEY_ID = "id"
    const val BOOK_KEY_PHONE = "phone_no"
    const val BOOK_KEY_PAYMENT = "payment"
    const val BOOK_KEY_DATE = "date"
    const val BOOK_KEY_PASSPORT = "passport"
    const val BOOK_KEY_SSN = "ssn"
    const val BOOK_KEY_ADDRESS = "address"

    //Persistent Data
    const val SHARED_PREF_NAME = "USER_SESSION"

    //Miscellaneous
    const val LOGGED_IN_USER_NAME = "LOGGED_IN_USER"
    const val LOGGED_IN_DISPLAY_NAME = "LOGGED_IN_USER_NAME"
}
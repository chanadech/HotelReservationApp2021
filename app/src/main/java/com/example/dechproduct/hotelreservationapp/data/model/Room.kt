package com.example.dechproduct.hotelreservationapp.data.model

import com.example.dechproduct.hotelreservationapp.util.Constants

data class Room (var roomNumber: String,
                 var roomType: String = Constants.ROOM_TYP_STANDARD,
                 var roomLocation: String = Constants.ROOM_LOC_NORMAL,
                 var roomBedType: String = Constants.ROOM_BED_SINGLE,
                 var maxCapacity: Int = Constants.ROOM_MAX_CAPACITY){
}
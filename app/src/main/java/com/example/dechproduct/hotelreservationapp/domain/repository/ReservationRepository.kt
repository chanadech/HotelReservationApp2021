package com.example.dechproduct.hotelreservationapp.domain.repository

import com.example.dechproduct.hotelreservationapp.data.model.Reservation
import com.example.dechproduct.hotelreservationapp.util.Resource

interface ReservationRepository {
    suspend fun addReservation(reservation:Reservation):Resource<Reservation>
    suspend fun editReservation(reservation:Reservation):Resource<Reservation>
    suspend fun removeReservation(reservation: Reservation):Resource<Reservation>
    suspend fun searchReservationByFName(firstName:String):Resource<Reservation>
    suspend fun searchReservationByLName(lastName:String):Resource<Reservation>
    suspend fun searchReservationByPhone(phoneNo:String):Resource<Reservation>
}
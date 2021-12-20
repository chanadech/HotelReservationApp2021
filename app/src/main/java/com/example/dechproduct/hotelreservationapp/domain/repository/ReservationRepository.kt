package com.example.dechproduct.hotelreservationapp.domain.repository

import com.example.dechproduct.hotelreservationapp.data.model.Reservation
import com.example.dechproduct.hotelreservationapp.util.Resource

interface ReservationRepository {
    suspend fun addReservation(reservation:Reservation):Resource<Reservation>
    suspend fun editReservation(reservation:Reservation):Resource<Reservation>
    suspend fun removeReservation(reservation: Reservation):Resource<Reservation>
    suspend fun searchReservation(keyword: String):Resource<MutableList<Reservation>>
    suspend fun populateReservation():Resource<MutableList<Reservation>>
}
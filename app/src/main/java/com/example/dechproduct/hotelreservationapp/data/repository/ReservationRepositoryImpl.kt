package com.example.dechproduct.hotelreservationapp.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.dechproduct.hotelreservationapp.data.model.Reservation
import com.example.dechproduct.hotelreservationapp.domain.repository.ReservationRepository
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.example.dechproduct.hotelreservationapp.util.Resource
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.time.Instant
import javax.inject.Inject

class ReservationRepositoryImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    val sharedPreferences: SharedPreferences): ReservationRepository {

    override suspend fun addReservation(reservation: Reservation):Resource<Reservation>{
        return try {
            //TODO: Migrate Database
            val bookingNode = firebaseDatabase.getReference(Constants.BOOK_DB_NODE)
            val uid = System.currentTimeMillis()

            bookingNode.child(uid.toString()).child(Constants.BOOK_KEY_FNAME).setValue(reservation.firstName)
            bookingNode.child(uid.toString()).child(Constants.BOOK_KEY_LNAME).setValue(reservation.lastName)
            bookingNode.child(uid.toString()).child(Constants.BOOK_KEY_PHONE).setValue(reservation.phoneNumber)
            bookingNode.child(uid.toString()).child(Constants.BOOK_KEY_PAYMENT).setValue(reservation.paymentType)
            bookingNode.child(uid.toString()).child(Constants.BOOK_KEY_SSN).setValue(reservation.ssnID)
            bookingNode.child(uid.toString()).child(Constants.BOOK_KEY_DATE).setValue(reservation.reserveDate)
            bookingNode.child(uid.toString()).child(Constants.BOOK_KEY_ADDRESS).setValue(reservation.address)

            Resource.Success(reservation)
        }

        catch (exception: Exception) {
            Log.d("ReserveRepositoryImpl",exception.toString())
            Resource.Failure(exception)
        }
    }

    override suspend fun searchReservation(reservation: Reservation):Resource<Reservation>{
        return try{
            Resource.Success(Reservation())
        }
        catch (exception: Exception){
            Resource.Failure(exception)
        }
    }

    override suspend fun editReservation(reservation: Reservation):Resource<Reservation>{
        return try{
            Resource.Success(Reservation())
        }
        catch (exception: Exception){
            Resource.Failure(exception)
        }
    }

    override suspend fun removeReservation(reservation: Reservation):Resource<Reservation>{
        return try{
            Resource.Success(Reservation())
        }
        catch (exception: Exception){
            Resource.Failure(exception)
        }
    }

}
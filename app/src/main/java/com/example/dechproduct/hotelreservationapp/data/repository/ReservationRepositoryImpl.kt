package com.example.dechproduct.hotelreservationapp.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.dechproduct.hotelreservationapp.data.model.Reservation
import com.example.dechproduct.hotelreservationapp.domain.repository.ReservationRepository
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.example.dechproduct.hotelreservationapp.util.Resource
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ReservationRepositoryImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    val sharedPreferences: SharedPreferences): ReservationRepository {

    override suspend fun addReservation(reservation: Reservation):Resource<Reservation>{
        return try {
            //TODO: Edit placeholder
            val bookingNode = firebaseDatabase.getReference(Constants.BOOK_DB_NODE).child("placeholder").get().await()
            val firstName = bookingNode.child(Constants.BOOK_KEY_FNAME).value.toString()
            val lastName = bookingNode.child(Constants.BOOK_KEY_LNAME).value.toString()

            Resource.Success(Reservation(firstName = firstName, lastName = lastName))
        }

        catch (exception: Exception) {
            Log.d("UserRepositoryImpl",exception.toString())
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

    override suspend fun searchReservationByFName(firstName:String):Resource<Reservation>{
        return try{
            Resource.Success(Reservation())
        }
        catch (exception: Exception){
            Resource.Failure(exception)
        }
    }

    override suspend fun searchReservationByLName(lastName:String):Resource<Reservation>{
        return try{
            Resource.Success(Reservation())
        }
        catch (exception: Exception){
            Resource.Failure(exception)
        }
    }

    override suspend fun searchReservationByPhone(phoneNo:String):Resource<Reservation>{
        return try{
            Resource.Success(Reservation())
        }
        catch (exception: Exception){
            Resource.Failure(exception)
        }
    }
}
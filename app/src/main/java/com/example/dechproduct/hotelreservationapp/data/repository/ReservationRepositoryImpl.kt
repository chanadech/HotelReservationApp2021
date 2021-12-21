package com.example.dechproduct.hotelreservationapp.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.dechproduct.hotelreservationapp.data.model.Reservation
import com.example.dechproduct.hotelreservationapp.domain.repository.ReservationRepository
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.example.dechproduct.hotelreservationapp.util.Resource
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ReservationRepositoryImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    val sharedPreferences: SharedPreferences): ReservationRepository {

    override suspend fun addReservation(reservation: Reservation): Resource<Reservation> {
        return try {
            //TODO: Migrate Database
            val bookingNode = firebaseDatabase.getReference(Constants.BOOK_DB_NODE)
            val uid = System.currentTimeMillis()

            bookingNode.child(uid.toString()).child(Constants.BOOK_KEY_FNAME)
                .setValue(reservation.firstName)
            bookingNode.child(uid.toString()).child(Constants.BOOK_KEY_LNAME)
                .setValue(reservation.lastName)
            bookingNode.child(uid.toString()).child(Constants.BOOK_KEY_PHONE)
                .setValue(reservation.phoneNumber)
            bookingNode.child(uid.toString()).child(Constants.BOOK_KEY_PAYMENT)
                .setValue(reservation.paymentType)
            bookingNode.child(uid.toString()).child(Constants.BOOK_KEY_SSN)
                .setValue(reservation.ssnID)
            bookingNode.child(uid.toString()).child(Constants.BOOK_KEY_PASSPORT)
                .setValue(reservation.passportID)
            bookingNode.child(uid.toString()).child(Constants.BOOK_KEY_DATE_IN)
                .setValue(reservation.reserveDateIn)
            bookingNode.child(uid.toString()).child(Constants.BOOK_KEY_DATE_OUT)
                .setValue(reservation.reserveDateOut)
            bookingNode.child(uid.toString()).child(Constants.BOOK_KEY_ADDRESS)
                .setValue(reservation.address)
            bookingNode.child(uid.toString()).child(Constants.BOOK_KEY_ID)
                .setValue(uid)

            Resource.Success(reservation)
        } catch (exception: Exception) {
            Log.d("ReserveRepositoryImpl", exception.toString())
            Resource.Failure(exception)
        }
    }

    override suspend fun searchReservation(keyword: String): Resource<MutableList<Reservation>> {
        return try {
            val bookingNode = firebaseDatabase.reference.child(Constants.BOOK_DB_NODE)
            var results: MutableList<Reservation> = mutableListOf<Reservation>()

            var test: String = ""

            bookingNode.orderByChild(Constants.BOOK_KEY_FNAME).equalTo(keyword).get()
                .await().children.map { item ->
                var reservation: Reservation = Reservation(
                    item.child(Constants.BOOK_KEY_ID).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_FNAME).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_LNAME).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_PHONE).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_PAYMENT).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_DATE_IN).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_DATE_OUT).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_PASSPORT).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_SSN).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_ADDRESS).getValue(String::class.java),
                )

                results.add(reservation)
                //Log.d("ReserveRepo",test)
            }

            bookingNode.orderByChild(Constants.BOOK_KEY_LNAME).equalTo(keyword).get()
                .await().children.map { item ->
                var reservation: Reservation = Reservation(
                    item.child(Constants.BOOK_KEY_ID).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_FNAME).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_LNAME).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_PHONE).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_PAYMENT).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_DATE_IN).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_DATE_OUT).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_PASSPORT).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_SSN).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_ADDRESS).getValue(String::class.java),
                )

                results.add(reservation)
                //Log.d("ReserveRepo",test)
            }

            Resource.Success(results)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun populateReservation(): Resource<MutableList<Reservation>> {
        return try {
            val bookingNode = firebaseDatabase.reference.child(Constants.BOOK_DB_NODE)
            var results: MutableList<Reservation> = mutableListOf<Reservation>()

            var test: String = ""

            bookingNode.orderByChild(Constants.BOOK_KEY_FNAME).get().await().children.map { item ->
                var reservation: Reservation = Reservation(
                    item.child(Constants.BOOK_KEY_ID).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_FNAME).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_LNAME).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_PHONE).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_PAYMENT).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_DATE_IN).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_DATE_OUT).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_PASSPORT).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_SSN).getValue(String::class.java),
                    item.child(Constants.BOOK_KEY_ADDRESS).getValue(String::class.java),
                )
                results.add(reservation)
                //Log.d("ReserveRepo",test)
            }
            Resource.Success(results)
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun editReservation(reservation: Reservation): Resource<Reservation> {
        return try {
            Resource.Success(Reservation())
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }

    override suspend fun removeReservation(reservation: Reservation): Resource<Reservation> {
        return try {
            Resource.Success(Reservation())
        } catch (exception: Exception) {
            Resource.Failure(exception)
        }
    }
}

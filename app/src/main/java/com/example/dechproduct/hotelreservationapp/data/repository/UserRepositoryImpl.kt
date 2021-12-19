package com.example.dechproduct.hotelreservationapp.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.dechproduct.hotelreservationapp.data.model.User
import com.example.dechproduct.hotelreservationapp.domain.repository.UserRepository
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.example.dechproduct.hotelreservationapp.util.Resource
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    val sharedPreferences: SharedPreferences): UserRepository{

    override suspend fun login(username: String, password: String):Resource<User> {

        return try {
            val userNode =
                firebaseDatabase.getReference(Constants.USER_DB_NODE).child(username).get().await()

            //TODO: Possible replace with query
            if(userNode.child(Constants.USER_KEY_PASSWORD).value.toString() == password){
                val userID = userNode.child(Constants.USER_KEY_ID).value.toString()
                val userName = userNode.child(Constants.USER_KEY_USERNAME).value.toString()
                val userDisplayName = userNode.child(Constants.USER_KEY_NAME).value.toString()

                Log.d("UserRepositoryImpl","Authentication Success.")
                Resource.Success(User(userID, userName, userDisplayName))
            }
            else
                throw Exception("Authentication Failed.")
        }

        catch (exception: Exception) {
            Log.d("UserRepositoryImpl",exception.toString())
            Resource.Failure(exception)
        }
    }

}
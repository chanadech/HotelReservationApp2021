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
            val userNode = firebaseDatabase.reference.child(Constants.USER_DB_NODE)
            var user: User = User("", "", "")

            userNode.orderByChild(Constants.USER_KEY_USERNAME).equalTo(username).get()
                .await().children.map { item ->
                    if (item.child(Constants.USER_KEY_PASSWORD).getValue(String::class.java) == password)
                    {
                        user.userID =
                            item.child(Constants.USER_KEY_ID).getValue(String::class.java)
                        user.userName =
                            item.child(Constants.USER_KEY_USERNAME).getValue(String::class.java)
                        user.userDisplayName =
                            item.child(Constants.USER_KEY_NAME).getValue(String::class.java)
                        Log.d("UserRepositoryImpl", "Authentication Success.")
                        Resource.Success(user)
                    }

                    else {
                        throw Exception("Authentication Failed.")
                    }
                }
            Log.d("UserRepositoryImpl", "Authentication Not really.")
            Resource.Success(user)
        }

        catch(exception: Exception) {
            Log.d("UserRepositoryImpl", exception.toString())
            Resource.Failure(exception)
        }

    }

}

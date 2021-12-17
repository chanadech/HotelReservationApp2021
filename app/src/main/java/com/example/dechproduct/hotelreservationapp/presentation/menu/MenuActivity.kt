package com.example.dechproduct.hotelreservationapp.presentation.menu

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.dechproduct.hotelreservationapp.R
import com.example.dechproduct.hotelreservationapp.databinding.ActivityMenu2Binding
import com.example.dechproduct.hotelreservationapp.presentation.login.LoginActivity
import com.example.dechproduct.hotelreservationapp.presentation.reservation.ReservationMenuActivity
import com.example.dechproduct.hotelreservationapp.util.Constants
import javax.inject.Inject

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenu2Binding

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_menu2
        )

        binding.btnBackMenu.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.cardView.setOnClickListener{
            val intent = Intent(this, ReservationMenuActivity::class.java)
            startActivity(intent)
        }
        updateLoginName()
    }

    private fun updateLoginName() {
        sharedPreferences = applicationContext.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val displayName = findViewById<TextView>(R.id.textView5)
        displayName.text = sharedPreferences.getString(Constants.LOGGED_IN_DISPLAY_NAME, null).toString()
    }

}
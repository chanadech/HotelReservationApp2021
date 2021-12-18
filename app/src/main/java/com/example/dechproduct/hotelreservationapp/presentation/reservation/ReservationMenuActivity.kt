package com.example.dechproduct.hotelreservationapp.presentation.reservation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.dechproduct.hotelreservationapp.R
import com.example.dechproduct.hotelreservationapp.databinding.ActivityReservationMenuBinding
import com.example.dechproduct.hotelreservationapp.presentation.menu.MenuActivity
import com.example.dechproduct.hotelreservationapp.presentation.reservation.add.AddReservationActivity
import com.example.dechproduct.hotelreservationapp.presentation.reservation.search.SearchReservationActivity
import com.example.dechproduct.hotelreservationapp.util.Constants
import javax.inject.Inject

class ReservationMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReservationMenuBinding

    @Inject
    lateinit var sharedPreferences:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_reservation_menu
        )

        binding.btnBackMenu.setOnClickListener{
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
        binding.cardView4.setOnClickListener{
            val intent = Intent(this, SearchReservationActivity::class.java)
            startActivity(intent)
        }
        binding.cardView.setOnClickListener{
            val intent = Intent(this, AddReservationActivity::class.java)
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
package com.example.dechproduct.hotelreservationapp.presentation.hotel.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.dechproduct.hotelreservationapp.R
import com.example.dechproduct.hotelreservationapp.databinding.ActivityMenu2Binding
import com.example.dechproduct.hotelreservationapp.presentation.hotel.login.LoginActivity
import com.example.dechproduct.hotelreservationapp.presentation.hotel.reservation.reservation_menu.ReservationMenuActivity

class MenuActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMenu2Binding
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

    }
}
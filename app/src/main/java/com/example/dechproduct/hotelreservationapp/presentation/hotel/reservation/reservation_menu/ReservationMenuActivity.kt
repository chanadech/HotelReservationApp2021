package com.example.dechproduct.hotelreservationapp.presentation.hotel.reservation.reservation_menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.dechproduct.hotelreservationapp.R
import com.example.dechproduct.hotelreservationapp.databinding.ActivityReservationMenuBinding
import com.example.dechproduct.hotelreservationapp.presentation.hotel.menu.MenuActivity2
import com.example.dechproduct.hotelreservationapp.presentation.hotel.reservation.reservation_add.AddReservationActivity

class ReservationMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReservationMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_reservation_menu
        )

        binding.btnBackMenu.setOnClickListener{
            val intent = Intent(this, MenuActivity2::class.java)
            startActivity(intent)
        }
        binding.cardView.setOnClickListener{
            val intent = Intent(this, AddReservationActivity::class.java)
            startActivity(intent)
        }


    }
}
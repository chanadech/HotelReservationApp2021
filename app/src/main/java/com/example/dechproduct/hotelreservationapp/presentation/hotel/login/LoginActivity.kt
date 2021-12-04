package com.example.dechproduct.hotelreservationapp.presentation.hotel.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.dechproduct.hotelreservationapp.R
import com.example.dechproduct.hotelreservationapp.databinding.ActivityLoginBinding
import com.example.dechproduct.hotelreservationapp.presentation.hotel.menu.MenuActivity
import com.example.dechproduct.hotelreservationapp.presentation.hotel.menu.MenuActivity2

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_login)

        binding.btSignIn.setOnClickListener{
            val intent = Intent(this,MenuActivity::class.java )
            startActivity(intent)
        }

        binding.tvSignIn.setOnClickListener{
            val intent = Intent(this, MenuActivity2::class.java)
            startActivity(intent)
        }


    }
}
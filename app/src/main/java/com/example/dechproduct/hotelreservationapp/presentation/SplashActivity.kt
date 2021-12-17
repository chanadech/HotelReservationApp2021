package com.example.dechproduct.hotelreservationapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.dechproduct.hotelreservationapp.presentation.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val runnable = Runnable {
        if (!isFinishing){
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable,2000)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }
}
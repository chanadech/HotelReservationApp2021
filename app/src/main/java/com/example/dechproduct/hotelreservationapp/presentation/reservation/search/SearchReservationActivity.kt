package com.example.dechproduct.hotelreservationapp.presentation.reservation.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.dechproduct.hotelreservationapp.databinding.ActivitySearchReservationactivityBinding
import com.example.dechproduct.hotelreservationapp.presentation.reservation.ReservationMenuActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchReservationActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySearchReservationactivityBinding
    private val searchReservationViewModel: SearchReservationViewModel by viewModels()

    //@Transient
    //var mContext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchReservationactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //here
        binding.btnBackMenu.setOnClickListener{
            val intent = Intent(this, ReservationMenuActivity::class.java)
            startActivity(intent)
            //mContext?.startActivity(intent)
        }

    }

}

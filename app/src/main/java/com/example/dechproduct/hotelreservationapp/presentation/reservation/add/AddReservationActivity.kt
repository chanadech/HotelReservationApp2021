package com.example.dechproduct.hotelreservationapp.presentation.reservation.add

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.dechproduct.hotelreservationapp.R
import com.example.dechproduct.hotelreservationapp.databinding.ActivityAddReservationBinding
import com.example.dechproduct.hotelreservationapp.presentation.reservation.ReservationMenuActivity
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class AddReservationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddReservationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_reservation)

        binding.buttonTest.setOnClickListener {
            showDateRangePicker()
        }

        binding.btnSubmit.setOnClickListener{
            Toast.makeText(applicationContext, "Mekh za 007", Toast.LENGTH_LONG).show()
        }

        binding.buttonCamera.setOnClickListener {
            Toast.makeText(applicationContext, "Mekh za 007 tap camera", Toast.LENGTH_LONG).show()

        }

        binding.btnBackMenu.setOnClickListener{
            val intent = Intent(this, ReservationMenuActivity::class.java)
            startActivity(intent)
        }

    }

    private fun showDateRangePicker() {
        val dateRangePicker = MaterialDatePicker.Builder
            .dateRangePicker()
            .setTitleText("Select Reservation Date ")
            .build()

        dateRangePicker.show(
            supportFragmentManager,
            "date_range_picker"
        )

        dateRangePicker.addOnPositiveButtonClickListener { datePicked ->

            val startDate = datePicked.first
            val endDate = datePicked.second
            if (startDate != null && endDate != null) {
                binding.tvDateStart.text = convertLongToDate(startDate)
                binding.tvDateEnd.text = convertLongToDate(endDate)

            }


        }
    }

    private fun convertLongToDate(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat(
            "dd-MM-yyyy",
            Locale.getDefault()
        )
        return format.format(date)
    }


}
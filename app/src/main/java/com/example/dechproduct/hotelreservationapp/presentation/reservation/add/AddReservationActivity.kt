package com.example.dechproduct.hotelreservationapp.presentation.reservation.add

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.dechproduct.hotelreservationapp.R
import com.example.dechproduct.hotelreservationapp.databinding.ActivityAddReservationBinding
import com.example.dechproduct.hotelreservationapp.databinding.ActivityLoginBinding
import com.example.dechproduct.hotelreservationapp.presentation.login.LoginViewModel
import com.example.dechproduct.hotelreservationapp.presentation.menu.MenuActivity
import com.example.dechproduct.hotelreservationapp.presentation.reservation.ReservationMenuActivity
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.example.dechproduct.hotelreservationapp.util.Resource
import com.google.android.material.datepicker.MaterialDatePicker
import com.omarshehe.forminputkotlin.FormInputMultiline
import com.omarshehe.forminputkotlin.FormInputSpinner
import com.omarshehe.forminputkotlin.FormInputText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class AddReservationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddReservationBinding
    private val addReservationViewModel: AddReservationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_reservation)

        binding.buttonTest.setOnClickListener {
            showDateRangePicker()
        }

        binding.btnSubmit.setOnClickListener{

            //TODO:Check no fields are blank
            var fname = findViewById<FormInputText>(R.id.first_name_customer).getValue()
            var lname = findViewById<FormInputText>(R.id.last_name_customer).getValue()
            var phone = findViewById<FormInputText>(R.id.phoneNumber).getValue()
            var payment = findViewById<FormInputSpinner>(R.id.payment_type).getValue()
            var id = findViewById<FormInputText>(R.id.ID).getValue()
            var sta_date = findViewById<TextView>(R.id.tvDateStart).text.toString()
            var end_date = findViewById<TextView>(R.id.tvDateEnd).text.toString()
            var address = findViewById<FormInputMultiline>(R.id.about).getValue()

            lifecycleScope.launch{
                addReservationViewModel.addReserve(fname,lname,phone,
                    payment,id,sta_date,end_date,address)
            }

            val intent = Intent(this@AddReservationActivity, MenuActivity::class.java)
            startActivity(intent)

        }

        binding.buttonCamera.setOnClickListener {
            Toast.makeText(applicationContext, "Camera Button is Tapped.", Toast.LENGTH_LONG).show()
            //TODO:Call camera here
        }

        binding.btnBackMenu.setOnClickListener{
            val intent = Intent(this@AddReservationActivity, MenuActivity::class.java)
            startActivity(intent)
        }
        observeSubmit()
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

    private fun observeSubmit() {
        addReservationViewModel.reserver.observe(this, {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { _ ->
                        Toast.makeText(applicationContext, "Reservation Success.",
                            Toast.LENGTH_SHORT).show()
                    }
                }

                is Resource.Failure -> {
                    Toast.makeText(applicationContext, it.throwable.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
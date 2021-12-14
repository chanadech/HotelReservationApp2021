package com.example.dechproduct.hotelreservationapp.presentation.hotel.reservation.reservation_search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.dechproduct.hotelreservationapp.R
import com.example.dechproduct.hotelreservationapp.databinding.ActivitySearchReservationactivityBinding
import com.example.dechproduct.hotelreservationapp.presentation.hotel.menu.MenuActivity2
import com.example.dechproduct.hotelreservationapp.presentation.hotel.reservation.reservation_menu.ReservationMenuActivity
import com.example.dechproduct.hotelreservationapp.presentation.hotel.reservation.reservation_search.adapter.NewsAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchReservationActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: SearchReservationViewModelFactory
    @Inject
    lateinit var newsAdapter: NewsAdapter
    lateinit var viewModel: SearchReservationViewModel
    private lateinit var binding:ActivitySearchReservationactivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchReservationactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnBackMenu.setOnClickListener{
            val intent = Intent(this, ReservationMenuActivity::class.java)
            startActivity(intent)
        }

        // เชื่อมหน้า fragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnvNews.setupWithNavController(
            navController
        )
        viewModel = ViewModelProvider(this,factory).get(SearchReservationViewModel::class.java)
    }


    }

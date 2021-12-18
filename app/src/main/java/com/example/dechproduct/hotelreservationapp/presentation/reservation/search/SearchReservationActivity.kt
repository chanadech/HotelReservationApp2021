package com.example.dechproduct.hotelreservationapp.presentation.reservation.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.dechproduct.hotelreservationapp.R
import com.example.dechproduct.hotelreservationapp.databinding.ActivitySearchReservationactivityBinding
import com.example.dechproduct.hotelreservationapp.presentation.reservation.ReservationMenuActivity
import com.example.dechproduct.hotelreservationapp.presentation.reservation.search.adapter.NewsAdapter
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

    @Transient
    var mContext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchReservationactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //here
        binding.btnBackMenu.setOnClickListener{
            val intent = Intent(this, ReservationMenuActivity::class.java)
            mContext?.startActivity(intent)
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

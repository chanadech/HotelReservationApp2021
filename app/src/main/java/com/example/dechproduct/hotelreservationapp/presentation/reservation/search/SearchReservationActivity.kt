package com.example.dechproduct.hotelreservationapp.presentation.reservation.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dechproduct.hotelreservationapp.R
import com.example.dechproduct.hotelreservationapp.databinding.ActivitySearchReservationactivityBinding
import com.example.dechproduct.hotelreservationapp.presentation.menu.MenuActivity
import com.example.dechproduct.hotelreservationapp.presentation.reservation.ReservationMenuActivity
import com.example.dechproduct.hotelreservationapp.presentation.reservation.search.adapter.ReservationsAdapter
import com.example.dechproduct.hotelreservationapp.util.Constants
import com.example.dechproduct.hotelreservationapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
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

        binding.btnBackMenu.setOnClickListener{
            val intent = Intent(this, ReservationMenuActivity::class.java)
            startActivity(intent)
            //mContext?.startActivity(intent)
        }

        var searchBar = findViewById<SearchView>(R.id.searchBar)
        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    lifecycleScope.launch{
                        searchReservationViewModel.searchReserve(query)
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    //TODO: Add search here, optional.
                    //Log.d("SearchResActivity",newText)
                    lifecycleScope.launch{
                        searchReservationViewModel.searchReserve(newText)
                    }
                    return false
                }
            })
        findViewById<RecyclerView>(R.id.reservationList).layoutManager = LinearLayoutManager(this)
        observeSearch()
    }

    private fun observeSearch() {
        searchReservationViewModel.reserver.observe(this, {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { reservationList ->
                        Log.d("SearchResActivity",reservationList.toString())
                        findViewById<RecyclerView>(R.id.reservationList).adapter = ReservationsAdapter(reservationList)
                    }
                }

                is Resource.Failure -> {
                    Toast.makeText(applicationContext, it.throwable.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}

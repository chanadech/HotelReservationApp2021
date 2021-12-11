package com.example.dechproduct.hotelreservationapp.presentation.hotel.reservation.reservation_search

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dechproduct.hotelreservationapp.domain.usecase.GetNewHeadlinesUseCase

class SearchReservationViewModelFactory(
    private val app: Application,
    private val getNewHeadlinesUseCase: GetNewHeadlinesUseCase
):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  SearchReservationViewModel(
            app,
            getNewHeadlinesUseCase
        )as T
    }
}
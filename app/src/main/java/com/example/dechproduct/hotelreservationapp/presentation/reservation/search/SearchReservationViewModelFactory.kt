package com.example.dechproduct.hotelreservationapp.presentation.reservation.search

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dechproduct.hotelreservationapp.domain.usecase.unused.GetNewHeadlinesUseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.unused.GetSearchedNewsUseCase

class SearchReservationViewModelFactory(
    private val app: Application,
    private val getNewHeadlinesUseCase: GetNewHeadlinesUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase
):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  SearchReservationViewModel(
            app,
            getNewHeadlinesUseCase,
            getSearchedNewsUseCase
        ) as T
    }
}
package com.example.dechproduct.hotelreservationapp.presentation.reservation.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dechproduct.hotelreservationapp.data.model.Reservation
import com.example.dechproduct.hotelreservationapp.domain.usecase.UseCase
import com.example.dechproduct.hotelreservationapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddReservationViewModel @Inject constructor(private val useCase: UseCase): ViewModel(){

    var reserver = MutableLiveData<Resource<Reservation>>()

    suspend fun addReserve(fname : String,lname  : String,phone : String,
                           payment : String,id : String,
                           sta_date : String,end_date : String,address : String){
        viewModelScope.launch {
            val reservation = useCase.addReserveUseCase(
                Reservation(firstName = fname, lastName = lname, phoneNumber = phone,
                paymentType = payment, ssnID = id, reserveDate = "$sta_date:$end_date",
                    address = address
                ))
            reserver.postValue(reservation)
        }
    }
}
package com.example.dechproduct.hotelreservationapp.domain.usecase

import com.example.dechproduct.hotelreservationapp.domain.usecase.login.LoginUseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.reservation.AddReserveUseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.reservation.EditReserveUseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.reservation.RemoveReserveUseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.reservation.SearchReserveUseCase

class UseCase(
    var loginUseCase: LoginUseCase,

    var addReserveUseCase: AddReserveUseCase,
    var searchReserveUseCase: SearchReserveUseCase,
    var editReserveUseCase: EditReserveUseCase,
    var removeReserveUseCase: RemoveReserveUseCase,
    ){}

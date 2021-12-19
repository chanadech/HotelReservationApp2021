package com.example.dechproduct.hotelreservationapp.domain.usecase

import com.example.dechproduct.hotelreservationapp.domain.usecase.login.LoginUseCase
import com.example.dechproduct.hotelreservationapp.domain.usecase.reservation.*

class UseCase(
    var loginUseCase: LoginUseCase,

    var addReserveUseCase: AddReserveUseCase,
    var searchReserveUseCase: SearchReserveUseCase,
    var populateReserveUseCase: PopulateReserveUseCase,
    var editReserveUseCase: EditReserveUseCase,
    var removeReserveUseCase: RemoveReserveUseCase,
    ){}

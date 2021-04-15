package ru.taksi.pro.android.mvvm.model.repo

import io.reactivex.rxjava3.core.Single
import okhttp3.RequestBody
import ru.taksi.pro.android.mvvm.model.entity.agregator.Agregator
import ru.taksi.pro.android.mvvm.model.entity.agregator.Agregators
import ru.taksi.pro.android.mvvm.model.entity.authorization.AuthorizationResponse
import ru.taksi.pro.android.mvvm.model.entity.authorization.ConfirmationCode
import ru.taksi.pro.android.mvvm.model.entity.cars.Car

interface ITaxiProRepository {

    // Get confirmation code
    fun requestCode(phone: String) : Single<ConfirmationCode>

    // Login by code
    fun loginByCode(code: String) : Single<AuthorizationResponse>

    // Get Agregators List
    fun getAgregatorsList() : Single<Agregators>

    // Get Agregator by "id"
    fun getAgregator(id: Int) : Single<Agregator>

    // Get Car by id
    fun getCar(id: Int) : Single<Car>
}
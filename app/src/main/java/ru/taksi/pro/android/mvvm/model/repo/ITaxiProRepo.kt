package ru.taksi.pro.android.mvvm.model.repo

import io.reactivex.rxjava3.core.Single
import ru.taksi.pro.android.mvvm.model.entity.agregator.Agregator
import ru.taksi.pro.android.mvvm.model.entity.agregator.Agregators
import ru.taksi.pro.android.mvvm.model.entity.cars.Car

interface ITaxiProRepo {

    // Get Agregators List
    fun getAgregatorsList() : Single<Agregators>

    // Get Agregator by "id"
    fun getAgregator(id: Int) : Single<Agregator>

    // Get Car by id
    fun getCar(id: Int) : Single<Car>
}
package ru.taksi.pro.android.mvvm.model.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.taksi.pro.android.mvvm.model.entity.agregator.Agregator
import ru.taksi.pro.android.mvvm.model.entity.agregator.Agregators
import ru.taksi.pro.android.mvvm.model.entity.cars.Car

interface IDataSource {

    // Get Agregators List
    @GET("api/v1/agregators")
    fun getAgregatorsList() : Single<Agregators>


    // Get Agregator by "id"
    @GET("api/v1/agregators/{id}")
    fun getAgregator(@Path("id") id: Int) : Single<Agregator>


    // Get Car by id
    @GET("api/v1/cars/{id}")
    fun getCar(@Path("id") id: Int) : Single<Car>
}

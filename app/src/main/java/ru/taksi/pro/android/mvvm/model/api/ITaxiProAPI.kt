package ru.taksi.pro.android.mvvm.model.api

import retrofit2.Call
import retrofit2.http.GET
import ru.taksi.pro.android.mvvm.model.entity.agregator.Agregators

interface ITaxiProAPI {
    @GET("api/v1/agregators")
    fun GetAgregatorsList() : Call<Agregators>
}

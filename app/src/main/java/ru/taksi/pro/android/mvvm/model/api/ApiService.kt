package ru.taksi.pro.android.mvvm.model.api

import io.reactivex.rxjava3.core.Single
import okhttp3.RequestBody
import retrofit2.http.*
import ru.taksi.pro.android.mvvm.model.entity.agregator.Agregator
import ru.taksi.pro.android.mvvm.model.entity.agregator.Agregators
import ru.taksi.pro.android.mvvm.model.entity.authorization.ConfirmationCode
import ru.taksi.pro.android.mvvm.model.entity.authorization.AuthorizationResponse
import ru.taksi.pro.android.mvvm.model.entity.cars.Car

interface ApiService {

//    @Headers("Content-Type: multipart/form-data; charset=UTF-8")
    @FormUrlEncoded
    @POST("sms/init")
    fun requestCode(@Field("phone") phone: String): Single<ConfirmationCode>

    @Headers("Content-Type: multipart/form-data; charset=utf-8")
    @FormUrlEncoded
    @POST("sms/login")
    fun loginByCode(@Field("code") code: String): Single<AuthorizationResponse>

    // Request confirmation code, HashMaap
    @Multipart
    @POST("sms/init")
    fun requestCode(@PartMap map: HashMap<String?, RequestBody?>) : Single<ConfirmationCode>

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

package ru.taksi.pro.android.mvvm.model.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.*
import ru.taksi.pro.android.mvvm.model.entity.agregator.Agregator
import ru.taksi.pro.android.mvvm.model.entity.agregator.AgregatorsList
import ru.taksi.pro.android.mvvm.model.entity.authorization.AuthorizationResponse
import ru.taksi.pro.android.mvvm.model.entity.authorization.ConfirmationCode
import ru.taksi.pro.android.mvvm.model.entity.authorization.RegistrationResponse
import ru.taksi.pro.android.mvvm.model.entity.balance.Balance
import ru.taksi.pro.android.mvvm.model.entity.cars.Car
import ru.taksi.pro.android.mvvm.model.entity.transaction.Transaction
import ru.taksi.pro.android.mvvm.model.entity.user.Profile
import ru.taksi.pro.android.mvvm.model.entity.user.Users
import java.util.*

interface ApiService {

    /***********************************************************************************************
     *                              Login API  -  /login/
     **********************************************************************************************/
    // User Registration - (phone + password + passwordRepeat)
    @FormUrlEncoded
    @POST("login/sign-up")
    fun registration(
        @Field("phone") phone: String,
        @Field("password") password: String,
        @Field("password_repeat") passwordRepeat: String
    ): Single<RegistrationResponse>

    // User Authentication - (phone + password)
    @FormUrlEncoded
    @POST("login/sign-up")
    fun authentication(
        @Field("phone") phone: String,
        @Field("password") password: String
    ): Single<RegistrationResponse>

    // Request confirmation SMS code
    @FormUrlEncoded
    @POST("login/init")
    fun requestCode(@Field("phone") phone: String): Single<ConfirmationCode>

    // Login - (phone + confirmation code)
    @FormUrlEncoded
    @POST("login/auth")
    fun loginByCode(
        @Field("phone") phone: String, @Field("code") code: String
    ): Single<AuthorizationResponse>


    /***********************************************************************************************
     *                         Aggregator API  -  api/v1/agregators
     **********************************************************************************************/
    @GET("api/v1/agregators")
    fun getAgregatorsList(): Single<AgregatorsList>

    @GET("api/v1/agregators/{id}")
    fun getAgregator(@Path("id") id: Int): Single<Agregator>


    /***********************************************************************************************
     *                          Balance API  -  api/v1/balance/
     **********************************************************************************************/
    @GET("api/v1/balance/{id}")
    fun getBalance(
        @Path("id") id: Int,
        @Header("Authorization") token: String
    ): Single<Balance>


    /***********************************************************************************************
     *                             Cars API  -  api/v1/cars/
     **********************************************************************************************/
    @GET("api/v1/cars")
    fun getAllCars(@Header("Authorization") token: String): Single<Car>

    @GET("api/v1/cars/{id}")
    fun getCar(@Path("id") id: Int,
               @Header("Authorization") token: String): Single<Car>

    @Multipart
    @POST("api/v1/cars")
    fun createNewCar(@Header("Authorization") token: String,
                     @Part("id") id: Int,
                     @Part("brand") brand: String,
                     @Part("model") model: String,
                     @Part("year") year: Int,
                     @Part("color") color: String,
                     @Part("registration") registration: String,
                     @Part("vin") vin: String,
                     @Part("sts") sts: String,
                     @Part("license") license: String,
                     @Part("id_users") id_users: Int): Single<Car>


    /***********************************************************************************************
     *                        Profile API  -  api/v1/profiles/{id}
     **********************************************************************************************/
    @GET("api/v1/profiles")
    fun getProfile(@Path("id") id: Int,
                   @Header("Authorization") token: String): Single<Profile>

    @Multipart
    @POST("api/v1/profiles")
    fun createProfile(@Header("Authorization") token: String,
                      @Part("firstname") firstName: String,
                      @Part("secondname") secondName: String,
                      @Part("lastname") lastName: String,
                      @Part("birthdate") birthDate: Date,
                      @Part("phone") phone: String,
                      @Part("passport_series") passportSeries: String,
                      @Part("passport_number") passportNumber: String,
                      @Part("passport_giver") passportGiver: String,
                      @Part("passport_date") passportDate: Date,
                      @Part("registration_address") registrationAddress: String,
                      @Part("license_series") licenseSeries: String,
                      @Part("license_number") licenseNumber: String,
                      @Part("license_date") licenseDate: Date,
                      @Part("license_expire") licenseExpire: Date,
                      @Part("user_id") userId: Int): Single<Profile>


    /***********************************************************************************************
     *                    Transaction API  -  api/v1/transactions/{id}
     **********************************************************************************************/
    @GET("api/v1/transactions/{id}")
    fun getTransactionByUserAgregators(
        @Path("id") id: Int,
        @Header("Authorization") token: String
    ): Single<Transaction>


    /***********************************************************************************************
     *                         Users API  -  api/v1/users/
     **********************************************************************************************/
    @GET("api/v1/users")
    fun getAllUsers(@Header("Authorization") token: String): Single<Users>

    @GET("api/v1/users/{id}")
    fun getUser(@Path("id") id: Int,
                @Header("Authorization") token: String): Single<Users>
}
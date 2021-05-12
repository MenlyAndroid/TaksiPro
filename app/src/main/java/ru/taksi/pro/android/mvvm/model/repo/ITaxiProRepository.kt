package ru.taksi.pro.android.mvvm.model.repo

import io.reactivex.rxjava3.core.Single
import ru.taksi.pro.android.mvvm.model.entity.agregator.Agregator
import ru.taksi.pro.android.mvvm.model.entity.agregator.AgregatorsList
import ru.taksi.pro.android.mvvm.model.entity.authorization.AuthorizationResponse
import ru.taksi.pro.android.mvvm.model.entity.authorization.ConfirmationCode
import ru.taksi.pro.android.mvvm.model.entity.authorization.RegistrationResponse
import ru.taksi.pro.android.mvvm.model.entity.authorization.User
import ru.taksi.pro.android.mvvm.model.entity.balance.Balance
import ru.taksi.pro.android.mvvm.model.entity.cars.Car
import ru.taksi.pro.android.mvvm.model.entity.tariffs.Tariff
import ru.taksi.pro.android.mvvm.model.entity.transaction.Transaction
import ru.taksi.pro.android.mvvm.model.entity.authorization.Profile
import ru.taksi.pro.android.mvvm.model.entity.user.Users
import kotlin.collections.HashMap

interface ITaxiProRepository {

    /***********************************************************************************************
     *                               Login API  -  /login/
     **********************************************************************************************/
    // Get SMS confirmation code
    fun requestCode(phone: String): Single<ConfirmationCode>
    fun loginByCode(phone: String, smsCode: String): Single<AuthorizationResponse>
    fun registration(
        phone: String,
        password: String,
        passwordRepeat: String
    ): Single<RegistrationResponse>

    fun authentication(phone: String, password: String): Single<RegistrationResponse>


    /***********************************************************************************************
     *                     Agregator API  -  api/v1/agregators
     **********************************************************************************************/
    fun getAgregatorsList(): Single<AgregatorsList>
    fun getAgregator(id: Int): Single<Agregator>

    /***********************************************************************************************
     *                       Tariffs API  -  api/v1/tariffs
     **********************************************************************************************/
    fun getTariffs(): Single<List<Tariff>>


    /***********************************************************************************************
     *                       Balance API  -  api/v1/balance/
     **********************************************************************************************/
    fun getBalance(id: Int, token: String): Single<Balance>


    /***********************************************************************************************
     *                          Cars API  -  api/v1/cars/
     **********************************************************************************************/
    fun getAllCars(token: String): Single<Car>
    fun getCar(id: Int, token: String): Single<Car>
    fun createNewCar(
        token: String,
        car: HashMap<String, Any>
    ): Single<Car>

    /***********************************************************************************************
     *                        Profile API  -  api/v1/profiles/{id}
     **********************************************************************************************/
    fun getProfile(id: Int, token: String): Single<Profile>

    fun createProfile(
        token: String,
        profile: HashMap<String, Any>
    ): Single<Profile>


    /***********************************************************************************************
     *                 Transaction API  -  api/v1/transactions/{id}
     **********************************************************************************************/
    fun getTransactionByUserAgregators(id: Int, token: String): Single<Transaction>


    /***********************************************************************************************
     *                          Users API  -  api/v1/users/
     **********************************************************************************************/
    fun getAllUsers(token: String): Single<Users>
    fun getUser(id: Int, token: String): Single<User>
}

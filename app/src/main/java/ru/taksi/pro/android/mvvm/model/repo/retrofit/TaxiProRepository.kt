package ru.taksi.pro.android.mvvm.model.repo.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.taksi.pro.android.mvvm.model.api.ApiService
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
import ru.taksi.pro.android.mvvm.model.network.INetworkChecker
import ru.taksi.pro.android.mvvm.model.repo.ITaxiProRepository
import kotlin.collections.HashMap

class TaxiProRepository(private val api: ApiService, private val networkChecker: INetworkChecker) : ITaxiProRepository {

    /***********************************************************************************************
     *                               Login API  -  /login/
     **********************************************************************************************/
    override fun registration(
        phone: String,
        password: String,
        passwordRepeat: String
    ): Single<RegistrationResponse> {
        return api.registration(phone, password, passwordRepeat).subscribeOn(Schedulers.io()) }

    override fun authentication(phone: String, password: String): Single<RegistrationResponse> {
        return api.authentication(phone, password).subscribeOn(Schedulers.io()) }

    // Get confirmation code by SMS
    override fun requestCode(phone: String): Single<ConfirmationCode> {
        return api.requestCode(phone).subscribeOn(Schedulers.io()) }

    // Login by SMS code
    override fun loginByCode(phone: String, smsCode: String): Single<AuthorizationResponse> {
        return api.loginByCode(phone, smsCode).subscribeOn(Schedulers.io()) }

    /***********************************************************************************************
     *                           Agregator API  -  api/v1/agregators
     **********************************************************************************************/
    override fun getAgregatorsList(): Single<AgregatorsList> {
        return api.getAgregatorsList().subscribeOn(Schedulers.io()) }

    override fun getAgregator(id: Int): Single<Agregator> {
        return api.getAgregator(id).subscribeOn(Schedulers.io()) }

    /***********************************************************************************************
     *                       Tariffs API  -  api/v1/tariffs
     **********************************************************************************************/

    override fun getTariffs(): Single<List<Tariff>> {
        return api.getTariffs().subscribeOn(Schedulers.io())
    }

    /***********************************************************************************************
     *                             Balance API  -  api/v1/balance/
     **********************************************************************************************/
    override fun getBalance(id: Int, token: String): Single<Balance> {
        return api.getBalance(id, "Bearer $token").subscribeOn(Schedulers.io()) }

    /***********************************************************************************************
     *                               Cars API  -  api/v1/cars/
     **********************************************************************************************/
    override fun getAllCars(token: String): Single<Car> {
        return api.getAllCars("Bearer $token").subscribeOn(Schedulers.io()) }

    override fun getCar(id: Int, token: String): Single<Car> {
        return api.getCar(id, "Bearer $token").subscribeOn(Schedulers.io()) }

    override fun createNewCar(token: String,
                              car: HashMap<String, Any>
    ): Single<Car> {
        return api.createNewCar(
            "Bearer $token", car) }

    /***********************************************************************************************
     *                        Profile API  -  api/v1/profiles/{id}
     **********************************************************************************************/
    override fun getProfile(id: Int, token: String): Single<Profile> {
        return api.getProfile(id, "Bearer $token").subscribeOn(Schedulers.io()) }

    override fun createProfile(
        token: String,
        profile: HashMap<String, Any>
    ): Single<Profile> {
        return api.createProfile("Bearer $token", profile)
            .subscribeOn(Schedulers.io())}

    /***********************************************************************************************
     *                    Transaction API  -  api/v1/transactions/{id}
     **********************************************************************************************/
    override fun getTransactionByUserAgregators(id: Int, token: String): Single<Transaction> {
        return api.getTransactionByUserAgregators(id, "Bearer $token")
            .subscribeOn(Schedulers.io()) }

    /***********************************************************************************************
     *                           Users API  -  api/v1/users/
     **********************************************************************************************/
    override fun getAllUsers(token: String): Single<Users> {
        return api.getAllUsers("Bearer $token").subscribeOn(Schedulers.io()) }

    override fun getUser(id: Int, token: String): Single<User> {
        return api.getUser(id, "Bearer $token").subscribeOn(Schedulers.io()) }
}

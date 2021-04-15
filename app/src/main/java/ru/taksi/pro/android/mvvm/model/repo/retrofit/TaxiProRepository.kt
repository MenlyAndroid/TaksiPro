package ru.taksi.pro.android.mvvm.model.repo.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.RequestBody
import ru.taksi.pro.android.mvvm.model.api.ApiService
import ru.taksi.pro.android.mvvm.model.entity.agregator.Agregator
import ru.taksi.pro.android.mvvm.model.entity.agregator.Agregators
import ru.taksi.pro.android.mvvm.model.entity.authorization.AuthorizationResponse
import ru.taksi.pro.android.mvvm.model.entity.authorization.ConfirmationCode
import ru.taksi.pro.android.mvvm.model.entity.cars.Car
import ru.taksi.pro.android.mvvm.model.repo.ITaxiProRepository

class TaxiProRepository(api: ApiService) : ITaxiProRepository {
    var api: ApiService

    init{
        this.api = api
    }

    // Get confirmation code
    override fun requestCode(phone: String): Single<ConfirmationCode> {
        return api.requestCode(phone).subscribeOn(Schedulers.io())
    }

    // Login
    override fun loginByCode(code: String): Single<AuthorizationResponse> {
        return api.loginByCode(code).subscribeOn(Schedulers.io())
    }

    // Get Agregators List
    override fun getAgregatorsList(): Single<Agregators>{
        return api.getAgregatorsList().subscribeOn(Schedulers.io())
    }

    // Get Agregator by "id"
    override fun getAgregator(id: Int): Single<Agregator>{
        return api.getAgregator(id).subscribeOn(Schedulers.io())
    }

    // Get Car by id - НЕ РАБОТАЕТ (HTTP 401 Unauthorized)
    override fun getCar(id: Int): Single<Car>{
        return api.getCar(id).subscribeOn(Schedulers.io())
    }
}

package ru.taksi.pro.android.mvvm.model.repo.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.taksi.pro.android.mvvm.model.api.IDataSource
import ru.taksi.pro.android.mvvm.model.entity.agregator.Agregator
import ru.taksi.pro.android.mvvm.model.entity.agregator.Agregators
import ru.taksi.pro.android.mvvm.model.entity.cars.Car
import ru.taksi.pro.android.mvvm.model.repo.ITaxiProRepo

class TaxiProRepo(api: IDataSource) : ITaxiProRepo {
    var api: IDataSource

    init{
        this.api = api
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

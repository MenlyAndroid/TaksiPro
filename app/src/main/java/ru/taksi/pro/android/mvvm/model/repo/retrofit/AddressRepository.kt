package ru.taksi.pro.android.mvvm.model.repo.retrofit

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.taksi.pro.android.mvvm.model.api.AddressApiService
import ru.taksi.pro.android.mvvm.model.api.ApiService
import ru.taksi.pro.android.mvvm.model.entity.address.Suggestions
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
import ru.taksi.pro.android.mvvm.model.repo.IAddressRepository
import ru.taksi.pro.android.mvvm.model.repo.ITaxiProRepository
import kotlin.collections.HashMap

class AddressRepository(private val api: AddressApiService, private val networkChecker: INetworkChecker) : IAddressRepository {

    /***********************************************************************************************
     *                               Login API  -  /login/
     **********************************************************************************************/

    override fun getVariants(body: HashMap<String, Any>): Single<Suggestions> {
        return api.getVariants(body).subscribeOn(Schedulers.io())
    }


}

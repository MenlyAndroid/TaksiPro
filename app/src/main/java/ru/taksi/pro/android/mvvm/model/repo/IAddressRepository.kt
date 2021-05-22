package ru.taksi.pro.android.mvvm.model.repo

import io.reactivex.rxjava3.core.Single
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
import kotlin.collections.HashMap

interface IAddressRepository {
    fun getVariants(
        body: HashMap<String, Any>
    ): Single<Suggestions>
}

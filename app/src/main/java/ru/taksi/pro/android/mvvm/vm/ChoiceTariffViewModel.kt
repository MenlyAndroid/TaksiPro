package ru.taksi.pro.android.mvvm.vm

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.taksi.pro.android.mvvm.data.UserProperties

class ChoiceTariffViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun setTariff(tariff: String) {
        UserProperties.instance.tariff = tariff
    }

    fun getTariff(): String? = UserProperties.instance.tariff

    public override fun onCleared() {
        compositeDisposable.dispose()
    }
}
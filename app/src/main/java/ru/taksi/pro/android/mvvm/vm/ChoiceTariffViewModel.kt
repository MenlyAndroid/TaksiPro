package ru.taksi.pro.android.mvvm.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import ru.taksi.pro.android.mvvm.data.UserProperties
import ru.taksi.pro.android.mvvm.model.entity.tariffs.Tariff
import ru.taksi.pro.android.mvvm.model.repo.ITaxiProRepository

class ChoiceTariffViewModel(
    private val iuSchedulers: Scheduler,
    private val repository: ITaxiProRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val answerLiveData = MutableLiveData<List<Tariff>>()
    fun getAnswerLiveData() = answerLiveData


    fun downloadTariffs() {
        repository.getTariffs()
            .doOnSuccess {
                answerLiveData.postValue(it)
            }.observeOn(iuSchedulers).subscribe({
            }, {
                answerLiveData.value = null
                it.printStackTrace()
                Log.d("!!!", "error${it.message}")
            }).addTo(compositeDisposable)
    }

    fun setTariff(tariff: String) {
        UserProperties.instance.tariff = tariff
    }

    fun getTariff(): String? = UserProperties.instance.tariff

    public override fun onCleared() {
        compositeDisposable.dispose()
    }
}
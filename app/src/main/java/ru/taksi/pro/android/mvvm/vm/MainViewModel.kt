package ru.taksi.pro.android.mvvm.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import ru.taksi.pro.android.mvvm.model.entity.agregator.Agregator
import ru.taksi.pro.android.mvvm.model.entity.tariffs.Tariff
import ru.taksi.pro.android.mvvm.model.repo.ITaxiProRepository

class MainViewModel(
    private val iuSchedulers: Scheduler,
    private val repository: ITaxiProRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val tariffsLiveData = MutableLiveData<List<Tariff>>()
    fun getTariffLiveData() = tariffsLiveData



    fun downloadTariffs() {
        repository.getTariffs()
            .doOnSuccess {
                tariffsLiveData.postValue(it)
            }.observeOn(iuSchedulers).subscribe({
            }, {
                tariffsLiveData.value = null
                it.printStackTrace()
                Log.d("!!!", "error${it.message}")
            }).addTo(compositeDisposable)
    }

    public override fun onCleared() {
        compositeDisposable.dispose()
    }
}
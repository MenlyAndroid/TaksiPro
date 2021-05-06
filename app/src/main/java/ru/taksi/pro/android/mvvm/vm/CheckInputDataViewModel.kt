package ru.taksi.pro.android.mvvm.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import ru.taksi.pro.android.mvvm.data.UserProperties
import ru.taksi.pro.android.mvvm.helpers.TextFormatHelper
import ru.taksi.pro.android.mvvm.model.repo.ITaxiProRepository

class CheckInputDataViewModel(
    private val iuSchedulers: Scheduler,
    private val repository: ITaxiProRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val answerLiveData = MutableLiveData<String>()
    fun getAnswerLiveData() = answerLiveData


    fun sendRegistrationData() {
        UserProperties.instance.let { property ->
            repository.createProfile(
                property.token ?: "",
                property.name ?: "",
                property.surname ?: "",
                property.patronymic ?: "",
                TextFormatHelper.createDateForApi(property.dateOfBird),
                property.phone ?: "",
                TextFormatHelper.getSeriesFromPassportData(property.passportData),
                TextFormatHelper.getNumberFromPassportData(property.passportData),
                property.whoIssued ?: "",
                TextFormatHelper.createDateForApi(property.dataOfIssued),
                TextFormatHelper.createStringAddress(
                    property.city,
                    property.district,
                    property.street,
                    property.home,
                    property.apartments
                ),
                TextFormatHelper.getSeriesFromPassportData(property.licenseNumber),
                TextFormatHelper.getNumberFromPassportData(property.licenseNumber),
                TextFormatHelper.createDateForApi(property.driverIssued),
                TextFormatHelper.createDateForApi(property.driverIssuedTo),
                property.userId ?: 0
            ).flatMap {
                property.userId = it.id
                repository.createNewCar(
                    property.token ?: "",
                    property.carBrand?: "",
                    property.carModel?: "",
                    property.carYear?.toInt()?: 0,
                    property.carColor?: "",
                    property.carNumber?: "",
                    property.carWIN?: "",
                    property.carCertificate?: "",
                    property.licenseNumber?: "",
                    it.id
                )
            }.doOnSuccess {
                answerLiveData.postValue("true")
            }.observeOn(iuSchedulers).subscribe({
            }, {
                answerLiveData.postValue(it.message)
                Log.d("!!!", "error")
            }).addTo(compositeDisposable)
        }
    }


    public override fun onCleared() {
        compositeDisposable.dispose()
    }
}
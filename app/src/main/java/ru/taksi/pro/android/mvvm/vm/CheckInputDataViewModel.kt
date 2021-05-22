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
            val profile: HashMap<String, Any> = hashMapOf(
                "firstname" to (property.name ?: ""),
                "secondname" to (property.surname ?: ""),
                "lastname" to (property.patronymic ?: ""),
                "birthdate" to (TextFormatHelper.createDateForApi(property.dateOfBird)),
                "phone" to (property.phone ?: ""),
                "passport_series" to (TextFormatHelper.getSeriesFromPassportData(property.passportData)),
                "passport_number" to (TextFormatHelper.getNumberFromPassportData(property.passportData)),
                "passport_giver" to (property.whoIssued ?: ""),
                "passport_date" to (TextFormatHelper.createDateForApi(property.dataOfIssued)),
                "registration_address" to (property.address ?: ""),
                "license_series" to (TextFormatHelper.getSeriesFromPassportData(property.licenseNumber)),
                "license_number" to (TextFormatHelper.getNumberFromPassportData(property.licenseNumber)),
                "license_date" to (TextFormatHelper.createDateForApi(property.driverIssued)),
                "license_expire" to (TextFormatHelper.createDateForApi(property.driverIssuedTo)),
                "user_id" to (property.userId ?: 0)
            )
            repository.createProfile(
                property.token ?: "",
                profile
            ).flatMap {
                UserProperties.instance.profile = it
                val newCar: HashMap<String, Any> = hashMapOf(
                    "id" to 0,
                    "brand" to (property.carBrand ?: ""),
                    "model" to (property.carModel ?: ""),
                    "year" to (property.carYear ?: ""),
                    "color" to (property.carColor ?: ""),
                    "registration" to (property.carNumber ?: ""),
                    "vin" to (property.carWIN ?: ""),
                    "sts" to (property.carCertificate ?: ""),
                    "license" to (property.licenseNumber ?: ""),
                    "id_users" to (property.userId ?: 0)
                )

                repository.createNewCar(
                    property.token ?: "",
                    newCar
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
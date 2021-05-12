package ru.taksi.pro.android.mvvm.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import ru.taksi.pro.android.mvvm.data.EventArgs
import ru.taksi.pro.android.mvvm.data.UserProperties
import ru.taksi.pro.android.mvvm.model.repo.ITaxiProRepository

class InputCarViewModel(
    private val iuSchedulers: Scheduler,
    private val repository: ITaxiProRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val answerLiveData = MutableLiveData<EventArgs>()
    fun getAnswerLiveData() = answerLiveData


    fun sendRegistrationData() {
        UserProperties.instance.let { property ->
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
            answerLiveData.postValue(EventArgs(null, EventArgs.DONE))
            UserProperties.instance.car = it
        }.observeOn(iuSchedulers).subscribe({
        }, {
            answerLiveData.postValue(EventArgs(it.message, EventArgs.ERROR))
            Log.d("!!!", "error")
        }).addTo(compositeDisposable)
    }


    public override fun onCleared() {
        compositeDisposable.dispose()
    }
}
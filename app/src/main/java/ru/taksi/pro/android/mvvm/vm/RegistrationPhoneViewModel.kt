package ru.taksi.pro.android.mvvm.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.taksi.pro.android.mvvm.model.repo.ITaxiProRepository
import io.reactivex.rxjava3.kotlin.addTo
import javax.inject.Inject

class RegistrationPhoneViewModel(
    private val iuSchedulers: Scheduler,
    private val repository: ITaxiProRepository
) : ViewModel() {

    private val answerLiveData = MutableLiveData<String>()
    fun getAnswerLiveData() = answerLiveData

    private val compositeDisposable = CompositeDisposable()

    fun sendPhone(phone: String) {
        repository.requestCode(phone).observeOn(iuSchedulers).subscribe({
            if (it.success == "true") {
                answerLiveData.value = "true"
            } else {
                answerLiveData.value = it.errors.tomanyrequests
            }
        }, {
            answerLiveData.value = it.message
            Log.d("!!!", "error")
        }).addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("!!!", "onCleared")
        compositeDisposable.dispose()
    }

}
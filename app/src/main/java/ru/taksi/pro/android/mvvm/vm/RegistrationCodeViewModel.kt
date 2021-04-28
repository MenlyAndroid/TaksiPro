package ru.taksi.pro.android.mvvm.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.taksi.pro.android.mvvm.model.repo.ITaxiProRepository
import io.reactivex.rxjava3.kotlin.addTo
import ru.taksi.pro.android.mvvm.data.UserProperties

class RegistrationCodeViewModel(
    private val iuSchedulers: Scheduler,
    private val repository: ITaxiProRepository
) : ViewModel() {

    private val answerLiveData = MutableLiveData<String>()
    fun getAnswerLiveData() = answerLiveData

    private val compositeDisposable = CompositeDisposable()

    fun sendCode(phone: String, code: String) {
        repository.loginByCode(phone, code).observeOn(iuSchedulers).subscribe({
            if (it.success == "true") {
                answerLiveData.value = "true"
                UserProperties.instance.token = it.user.token
            } else {
                answerLiveData.value = it.success
            }
        }, {
            answerLiveData.value = it.message
            Log.d("!!!", "error")
        }).addTo(compositeDisposable)
    }

    public override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}
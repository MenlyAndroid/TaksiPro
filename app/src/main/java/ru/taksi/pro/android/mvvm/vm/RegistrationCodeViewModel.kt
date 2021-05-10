package ru.taksi.pro.android.mvvm.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import ru.taksi.pro.android.mvvm.data.UserProperties
import ru.taksi.pro.android.mvvm.model.repo.ITaxiProRepository

class RegistrationCodeViewModel(
    private val iuSchedulers: Scheduler,
    private val repository: ITaxiProRepository
) : ViewModel() {

    companion object {
        const val CODE_NEW_USER = "user"
        const val CODE_AUTHORISED_USER = "authorised"
    }

    private val answerLiveData = MutableLiveData<String>()
    fun getAnswerLiveData() = answerLiveData

    private val compositeDisposable = CompositeDisposable()

    fun sendCode(phone: String, code: String) {
        repository.loginByCode(phone, code).observeOn(iuSchedulers).subscribe({
            if (it.success == "true") {
                if (it.user.profile == null) {
                    answerLiveData.value = CODE_NEW_USER
                    UserProperties.instance.token = it.user.token
                    UserProperties.instance.userId = it.user.id
                } else {
                    answerLiveData.value = CODE_AUTHORISED_USER
                    UserProperties.instance.token = it.user.token
                    UserProperties.instance.userId = it.user.id
                }
            } else {
                answerLiveData.value = it.success
            }
        }, {
            answerLiveData.value = it.message
            it.printStackTrace()
        }).addTo(compositeDisposable)
    }

    public override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}
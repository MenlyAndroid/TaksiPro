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

class DataBalanceViewModel(
    private val iuSchedulers: Scheduler,
    private val repository: ITaxiProRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val answerLiveData = MutableLiveData<EventArgs>()
    fun getAnswerLiveData() = answerLiveData


    fun getUserProfile() {
        Log.d("!!!", "getUserProfile")
        answerLiveData.value = (EventArgs(null, EventArgs.PROCESS))
        Log.d("!!!", "getUserProfile2 ${UserProperties.instance.profile} ${UserProperties.instance.token}")
        UserProperties.instance.profile?.id?.let { id ->
            UserProperties.instance.token?.let { token ->
                Log.d("!!!", "id: $id token: $token")
                repository.getProfile(id, token)
                    .doOnSuccess {
                        UserProperties.instance.profile = it
                        answerLiveData.postValue(EventArgs("true", EventArgs.DONE))
                    }.observeOn(iuSchedulers).subscribe({
                    }, {
                        answerLiveData.postValue(EventArgs(it.message, EventArgs.ERROR))
                        Log.d("!!!", "error")
                    }).addTo(compositeDisposable)
            }
        }
    }

    public override fun onCleared() {
        compositeDisposable.dispose()
    }
}
package ru.taksi.pro.android.mvvm.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import ru.taksi.pro.android.mvvm.model.repo.IAddressRepository

class InputPassportDataViewModel(
    private val iuSchedulers: Scheduler,
    private val repository: IAddressRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val answerLiveData = MutableLiveData<List<String>>()
    fun getAnswerLiveData() = answerLiveData

    fun getVariants(address: String) {
        val body: HashMap<String, Any> = hashMapOf(
            "query" to address
        )
        repository.getVariants(body).observeOn(iuSchedulers).subscribe({
            val addressList = mutableListOf<String>()
            for (suggestion in it.suggestions) {
                addressList.add(suggestion.value)
            }
            answerLiveData.postValue(addressList)
        }, {
            Log.d("!!!", "error")
        }).addTo(compositeDisposable)
    }

    public override fun onCleared() {
        compositeDisposable.dispose()
    }
}
package ru.taksi.pro.android.mvvm.vm

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.taksi.pro.android.mvvm.data.UserProperties

class ChoiceAggregatorViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun setAggregator(aggregator: String) {
        UserProperties.instance.aggregators.add(aggregator)
    }

    fun removeAggregator(aggregator: String) {
        UserProperties.instance.aggregators.remove(aggregator)
    }

    fun getAggregators(): Set<String> = UserProperties.instance.aggregators


    public override fun onCleared() {
        compositeDisposable.dispose()
    }
}
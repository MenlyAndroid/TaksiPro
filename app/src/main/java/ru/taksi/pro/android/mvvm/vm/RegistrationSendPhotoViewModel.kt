package ru.taksi.pro.android.mvvm.vm

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Scheduler
import ru.taksi.pro.android.mvvm.model.repo.ITaxiProRepository

class RegistrationSendPhotoViewModel(
    private val iuSchedulers: Scheduler,
    private val repository: ITaxiProRepository
) : ViewModel() {

}
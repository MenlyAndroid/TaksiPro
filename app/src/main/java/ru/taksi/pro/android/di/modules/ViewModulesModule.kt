package ru.taksi.pro.android.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.core.Scheduler
import ru.taksi.pro.android.mvvm.model.repo.ITaxiProRepository
import ru.taksi.pro.android.mvvm.vm.RegistrationAddPhotoViewModel
import ru.taksi.pro.android.mvvm.vm.RegistrationCodeViewModel
import ru.taksi.pro.android.mvvm.vm.RegistrationPhoneViewModel
import ru.taksi.pro.android.mvvm.vm.RegistrationSendPhotoViewModel
import javax.inject.Singleton
import ru.taksi.pro.android.mvvm.vm.*

@Module
class ViewModulesModule {

    @Provides
    fun getRegistrationPhoneViewModel(
        uiSchedulers: Scheduler,
        repository: ITaxiProRepository
    ) = RegistrationPhoneViewModel(uiSchedulers, repository)

    @Provides
    fun getRegistrationCodeViewModel(
        uiSchedulers: Scheduler,
        repository: ITaxiProRepository
    ) = RegistrationCodeViewModel(uiSchedulers, repository)

    @Provides
    fun getRegistrationAddPhotoViewModel(
        uiSchedulers: Scheduler,
        repository: ITaxiProRepository
    ) = RegistrationAddPhotoViewModel(uiSchedulers, repository)

    @Provides
    fun getRegistrationSendPhotoViewModel(
        uiSchedulers: Scheduler,
        repository: ITaxiProRepository
    ) = RegistrationSendPhotoViewModel(uiSchedulers, repository)
    fun getChoiceTariffViewModel() = ChoiceTariffViewModel()

    @Provides
    fun getChoiceAggregatorViewModel() = ChoiceAggregatorViewModel()
}
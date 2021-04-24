package ru.taksi.pro.android.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.core.Scheduler
import ru.taksi.pro.android.mvvm.model.repo.ITaxiProRepository
import ru.taksi.pro.android.mvvm.vm.RegistrationCodeViewModel
import ru.taksi.pro.android.mvvm.vm.RegistrationPhoneViewModel
import javax.inject.Singleton

@Module
class ViewModulesModule {
    @Singleton
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
}
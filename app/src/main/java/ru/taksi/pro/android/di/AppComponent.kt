package ru.taksi.pro.android.di

import dagger.Component
import ru.taksi.pro.android.di.modules.ApiModule
import ru.taksi.pro.android.di.modules.AppModule
import ru.taksi.pro.android.di.modules.RepoModule
import ru.taksi.pro.android.di.modules.ViewModulesModule
import ru.taksi.pro.android.ui.activities.MainActivity
import ru.taksi.pro.android.ui.fragments.*
import ru.taksi.pro.android.mvvm.vm.RegistrationAddPhotoViewModel
import ru.taksi.pro.android.ui.activities.DataBalanceActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        RepoModule::class,
        ViewModulesModule::class
    ]
)
interface AppComponent {
    fun inject(registrationFragment: RegistrationFragment)
    fun inject(registrationFragmentEnterCode: RegistrationFragmentEnterCode)
    fun inject(registrationAddPhotoViewModel: RegistrationAddPhotoViewModel)
    fun inject(registrationFragmentChoiceTariff: RegistrationFragmentChoiceTariff)
    fun inject(registrationFragmentChoiceAggregator: RegistrationFragmentChoiceAggregator)
    fun inject(registrationFragmentCheckInputData: RegistrationFragmentCheckInputData)
    fun inject(mainActivity: MainActivity)
    fun inject(dataBalanceActivity: DataBalanceActivity)
    fun inject(registrationFragmentInputCar: RegistrationFragmentInputCar)
    fun inject(registrationFragmentInputPassportData: RegistrationFragmentInputPassportData)
}
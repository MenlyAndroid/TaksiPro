package ru.taksi.pro.android.di

import dagger.Component
import ru.taksi.pro.android.di.modules.ApiModule
import ru.taksi.pro.android.di.modules.AppModule
import ru.taksi.pro.android.di.modules.RepoModule
import ru.taksi.pro.android.di.modules.ViewModulesModule
import ru.taksi.pro.android.domain.fragments.RegistrationFragment
import ru.taksi.pro.android.domain.fragments.RegistrationFragmentEnterCode
import ru.taksi.pro.android.mvvm.vm.RegistrationAddPhotoViewModel
import ru.taksi.pro.android.domain.fragments.*
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
    fun inject(registratonFragmentChoiceTariff: RegistratonFragmentChoiceTariff)
    fun inject(registrationFragmentChoiceAggregator: RegistrationFragmentChoiceAggregator)
    fun inject(registrationFragmentCheckInputData: RegistrationFragmentCheckInputData)
}
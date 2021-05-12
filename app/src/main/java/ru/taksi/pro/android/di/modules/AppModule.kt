package ru.taksi.pro.android.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import ru.taksi.pro.android.app.TaxiProApplication
import ru.taksi.pro.android.ui.spsettings.SPSettingsRepo
import ru.taksi.pro.android.mvvm.model.spsettings.ISPSettingsRepo
import javax.inject.Singleton

@Module
class AppModule(private val app: TaxiProApplication) {

    @Provides
    fun app() = app

    @Provides
    fun getUISchelduler() = AndroidSchedulers.mainThread()

    @Singleton
    @Provides
    fun getSPSettingsRepo(): ISPSettingsRepo = SPSettingsRepo(app)
}
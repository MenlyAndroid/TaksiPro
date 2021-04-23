package ru.taksi.pro.android.app

import android.app.Application
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import ru.taksi.pro.android.di.AppComponent
import ru.taksi.pro.android.di.DaggerAppComponent
import ru.taksi.pro.android.di.modules.AppModule

class TaxiProApplication : Application(){
    companion object {
        lateinit var instance: TaxiProApplication
        val component get() = instance._appComponent
    }

    private lateinit var _appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler { throwable: Throwable? -> {} }
        instance = this
        _appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}

package ru.taksi.pro.android

import android.app.Application

class TaxiProApplication : Application(){
    val INSTANCE : TaxiProApplication
    val apiHolder : ApiHolder

    init {
        INSTANCE = this
        apiHolder = ApiHolder()
    }
}

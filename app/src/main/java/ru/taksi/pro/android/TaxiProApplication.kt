package ru.taksi.pro.android

import android.app.Application

class TaxiProApplication : Application(){
    val INSTANCE : TaxiProApplication = this
    val apiHolder : ApiHolder = ApiHolder()

}

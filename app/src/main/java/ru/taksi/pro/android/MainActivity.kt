package ru.taksi.pro.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.MediaType
import okhttp3.RequestBody
import ru.taksi.pro.android.mvvm.model.api.ApiService
import ru.taksi.pro.android.mvvm.model.repo.retrofit.TaxiProRepository


class MainActivity : AppCompatActivity() {
    val TAG = "TaxiPro"         // Тэг для логирования
    val dataSource: ApiService = TaxiProApplication().INSTANCE.apiHolder.getApi()
    val api = TaxiProRepository(dataSource)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestCode("79251234567")    // Отправляем номер телефона. Запрашиваем код авторизации
        Thread.sleep(1000)             // Для последовательности
        loginByCode("0000")            // Отправляем код авторизации
    }


    //  Пример запроса кода подтверждения авторизации
    fun requestCode(phone: String) {
        api.requestCode(phone).observeOn(AndroidSchedulers.mainThread()).subscribe({
            Log.i("TaxiPro", "requestCode Success: " + it)
            textView.text = it.toString()
        }, {
            Log.i("TaxiPro", "requestCode Throwable: " + it)
        })
    }


    // Login by code
    fun loginByCode(code: String){
        api.loginByCode(code).observeOn(AndroidSchedulers.mainThread()).subscribe({
            Log.i("TaxiPro", "loginByCode Success: " + it)
            textView.text = it.toString()
        }, {
            Log.i("TaxiPro", "loginByCode Throwable: " + it)
        })
    }


    // Пример запроса списка агрегаторов
    fun agregatorsList() {
        api.getAgregatorsList().observeOn(AndroidSchedulers.mainThread()).subscribe({
            Log.i("TaxiPro", "getAgregatorsList: " + it)
        }, {
            Log.i("TaxiPro", "getAgregatorsList: Throwable " + it)
        })
    }

    // Пример запроса одного агрегатора
    fun agregator(id: Int) {
        api.getAgregator(id).observeOn(AndroidSchedulers.mainThread()).subscribe({
            Log.i("TaxiPro", "getAgregator: " + it)
        }, {
            Log.i("TaxiPro", "getAgregator: Throwable " + it)
        })
    }

    // Пример запроса автомообиля  - НЕ РАБОТАЕТ (HTTP 401 Unauthorized)
    fun car(id: Int) {
        api.getCar(id).observeOn(AndroidSchedulers.mainThread()).subscribe({
            Log.i("TaxiPro", "getCar: " + it)
        }, {
            Log.i("TaxiPro", "getCar: Throwable " + it)
        })
    }
}
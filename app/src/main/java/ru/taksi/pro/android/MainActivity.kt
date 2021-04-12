package ru.taksi.pro.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import ru.taksi.pro.android.mvvm.model.api.IDataSource
import ru.taksi.pro.android.mvvm.model.repo.retrofit.TaxiProRepo


class MainActivity : AppCompatActivity() {
    val TAG = "TaxiPro"         // Тэг для логирования
    val api: IDataSource = TaxiProApplication().INSTANCE.apiHolder.getApi()
    val agregator = TaxiProRepo(api)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        agregatorsList()        // Пример запроса списка агрегаторов
        agregator(1)        // Пример запроса одного агрегатора
        car(1)              // Пример запроса автомообиля - НЕ РАБОТАЕТ (HTTP 401 Unauthorized)
    }

    // Пример запроса списка агрегаторов
    fun agregatorsList() {
        agregator.getAgregatorsList().observeOn(AndroidSchedulers.mainThread()).subscribe({
            Log.i("TaxiPro", "getAgregatorsList: " + it)
            agragatorsList.text = it.toString()
            //TODO("Добавить обработчик успешного получения списка агрегаторов")
        }, {
            Log.i("TaxiPro", "getAgregatorsList: Throwable")
            agragatorsList.text = it.toString()
            //TODO("Добавить обработчик ошибки")
        })
    }

    // Пример запроса одного агрегатора
    fun agregator(id: Int){
        agregator.getAgregator(id).observeOn(AndroidSchedulers.mainThread()).subscribe({
            Log.i("TaxiPro", "getAgregator: " + it)
            agragator.text = it.toString()
            //TODO("Добавить обработчик успешного получения агрегатора по id")
        }, {
            Log.i("TaxiPro", "getAgregator: Throwable " + it)
            agragator.text = it.toString()
            //TODO("Добавить обработчик ошибки")
        })
    }

    // Пример запроса автомообиля  - НЕ РАБОТАЕТ (HTTP 401 Unauthorized)
    fun car(id: Int){
        agregator.getCar(id).observeOn(AndroidSchedulers.mainThread()).subscribe({
            Log.i("TaxiPro", "getCar: " + it)
            car.text = it.toString()
            //TODO("Добавить обработчик успешного получения агрегатора по id")
        }, {
            Log.i("TaxiPro", "getCar: Throwable " + it)
            car.text = it.toString()
            //TODO("Добавить обработчик ошибки")
        })
    }
}
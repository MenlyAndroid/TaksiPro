package ru.taksi.pro.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import ru.taksi.pro.android.app.TaxiProApplication
import ru.taksi.pro.android.mvvm.model.api.ApiService
import ru.taksi.pro.android.mvvm.model.repo.retrofit.TaxiProRepository

class MainActivity : AppCompatActivity() {
//    private val dataSource: ApiService = TaxiProApplication().INSTANCE.apiHolder.getApi()
//    private val api = TaxiProRepository(dataSource)
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
////        requestCode("79251234567")    // Отправляем номер телефона. Запрашиваем код авторизации
//        requestCode("79257654321")    // Отправляем номер телефона. Запрашиваем код авторизации
//        Thread.sleep(500)              // Исключаем Race Condition
//        loginByCode("79257654321", "0000")    // Отправляем код авторизации
//        Thread.sleep(500)              // Исключаем Race Condition
//
////   Примеры запросов
//        val token = "RZ9UD3iemtjn-x860ZjbwL8r167oFRq_"
//        getCar(2, token)
//        getUser(2, token)
////        createNewCar(token, 1, "Mercedes", "600", 2000, "синий",
////            "string", "XXXXXX1", "XXXXXX1", "XXXXXXXXX1", 2)
//    }
//
//    //  Пример запроса кода подтверждения авторизации
//    private fun requestCode(phone: String) {
//        api.requestCode(phone).observeOn(AndroidSchedulers.mainThread()).subscribe({/*Success*/}, {/*Error*/}) }
//
//    // Пример Login by code
//    private fun loginByCode(phone: String, code: String){
//        api.loginByCode(phone, code).observeOn(AndroidSchedulers.mainThread()).subscribe({/*Success*/}, {/*Error*/}) }
//
//    fun agregatorsList() {                  // Пример запроса списка агрегаторов
//        api.getAgregatorsList().observeOn(AndroidSchedulers.mainThread()).subscribe({/*Success*/}, {/*Error*/}) }
//
//    fun agregator(id: Int) {                // Пример запроса одного агрегатора
//        api.getAgregator(id).observeOn(AndroidSchedulers.mainThread()).subscribe({/*Success*/}, {/*Error*/}) }
//
//    fun getBalance(id: Int, token: String){ // Пример заапроса баланса
//        api.getBalance(id, token).observeOn(AndroidSchedulers.mainThread()).subscribe({/*Success*/}, {/*Error*/}) }
//
//    fun getCar(id: Int, token: String){
//        api.getCar(id, token).observeOn(AndroidSchedulers.mainThread()).subscribe({/*Success*/}, {/*Error*/}) }
//
//    fun getUser(id: Int, token: String){
//        api.getUser(id, token).observeOn(AndroidSchedulers.mainThread()).subscribe({/*Success*/}, {/*Error*/}) }
//
//    fun createNewCar(token: String, id: Int, brand: String, model: String, year: Int, color: String,
//                     registration: String, vin: String, sts: String, license: String, userId: Int){
//        api.createNewCar(token, id, brand, model, year, color, registration, vin, sts, license, userId)
//            .observeOn(AndroidSchedulers.mainThread()).subscribe({/*Success*/}, {/*Error*/}) }
}

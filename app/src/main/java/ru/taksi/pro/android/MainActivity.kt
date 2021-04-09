package ru.taksi.pro.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.taksi.pro.android.mvvm.model.api.ITaxiProAPI
import ru.taksi.pro.android.mvvm.model.entity.agregator.Agregators

class MainActivity : AppCompatActivity() {
    val TAG = "TaxiPro"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        loadData()
    }

    internal fun loadData() {
        Log.i(TAG, "LoadData")
        val retrofit = Retrofit.Builder()
                .baseUrl("http://menly.1site.space/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(ITaxiProAPI::class.java)
        val call = service.GetAgregatorsList()

        call.enqueue(object : Callback<Agregators> {
            override fun onResponse(call: Call<Agregators>,
                                    response: Response<Agregators>) {
                Log.i(TAG, "onResponse" + response)
                responseBody.setText(responseBody.toString())
                if (response.code() == 200) {
                    responseBody.setText(response.body().toString())
                }
            }

            override fun onFailure(call: Call<Agregators>, t: Throwable) {
                Log.i(TAG, "onFailure: " + t)
            }
        })
    }
}
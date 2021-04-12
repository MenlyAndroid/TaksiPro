package ru.taksi.pro.android

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.taksi.pro.android.mvvm.model.api.IDataSource

class ApiHolder{
    val baseUrl = "http://89.108.71.31:8000"
    private var dataSource: IDataSource

    init{
        val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

        dataSource = retrofit.create(IDataSource::class.java)
    }

    fun getApi() = dataSource
}
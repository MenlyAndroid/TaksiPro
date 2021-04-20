package ru.taksi.pro.android

import com.google.gson.GsonBuilder
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.taksi.pro.android.mvvm.model.api.ApiService
import java.net.CookieHandler
import java.net.CookieManager
import java.util.concurrent.TimeUnit

class ApiHolder {
    private val baseUrl = "http://89.108.71.31:8000"
    private var dataSource: ApiService

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val cookieHandler: CookieHandler = CookieManager()

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .cookieJar(JavaNetCookieJar(cookieHandler))
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)

        val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client.build())
                .build()

        dataSource = retrofit.create(ApiService::class.java)
    }

    fun getApi() = dataSource
}

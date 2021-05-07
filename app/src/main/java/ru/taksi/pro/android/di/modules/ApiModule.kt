package ru.taksi.pro.android.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import ru.taksi.pro.android.mvvm.model.api.ApiService
import java.net.CookieHandler
import java.net.CookieManager
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {
    private val baseUrl = "http://89.108.71.31:8000"
    @Singleton
    @Provides
    fun gson() = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

    @Singleton
    @Provides
    fun getInterceptor() =
        HttpLoggingInterceptor().apply { this.level = HttpLoggingInterceptor.Level.BODY }

    @Singleton
    @Provides
    fun getCookieHandler(): CookieHandler = CookieManager()

    @Singleton
    @Provides
    fun client(interceptor: HttpLoggingInterceptor, cookieHandler: CookieHandler) =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .cookieJar(JavaNetCookieJar(cookieHandler))
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()

    @Singleton
    @Provides
    fun api(gson: Gson, client: OkHttpClient) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()
        .create(ApiService::class.java)
}
package ru.taksi.pro.android.mvvm.model.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.*
import ru.taksi.pro.android.mvvm.model.entity.address.Suggestions


interface AddressApiService {


    @Headers(
        "Content-Type: application/json",
        "Accept: application/json",
        "Authorization: Token 88bd3e9d4b65c8a9bdf88cb473566cdae6ef5585"
    )
    @POST("/suggestions/api/4_1/rs/suggest/address")
    fun getVariants(@Body body: HashMap<String, Any>): Single<Suggestions>
}

package ru.taksi.pro.android.mvvm.model.entity.cars

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Car(
    @Expose val brand: String,
    @Expose val color: String,
    @Expose val id: Int,
    @Expose val id_users: Int,
    @Expose val license: String,
    @Expose val model: String,
    @Expose val registration: String,
    @Expose val sts: String,
    @Expose val vin: String,
    @Expose val year: String
) : Parcelable
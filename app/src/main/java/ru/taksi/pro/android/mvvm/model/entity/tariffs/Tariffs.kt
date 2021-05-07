package ru.taksi.pro.android.mvvm.model.entity.tariffs

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

class Tariffs : ArrayList<Tariff>()

@Parcelize
data class Tariff(
    @Expose val id: Int,
    @Expose val name: String,
    @Expose val subscription: Int,
    @Expose val rate: Int,
    @Expose val description: String
) : Parcelable

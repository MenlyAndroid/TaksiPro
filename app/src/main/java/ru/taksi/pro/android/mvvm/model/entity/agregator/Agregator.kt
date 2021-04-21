package ru.taksi.pro.android.mvvm.model.entity.agregator

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

class AgregatorsList : ArrayList<Agregator>()

@Parcelize
data class Agregator(
    @Expose val id: Int,
    @Expose val logo: String,
    @Expose val name: String
) : Parcelable
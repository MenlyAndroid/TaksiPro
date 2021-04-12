package ru.taksi.pro.android.mvvm.model.entity.agregator

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

class Agregators : ArrayList<AgregatorsItem>()

@Parcelize
data class AgregatorsItem(
    @Expose val id: Int,
    @Expose val name: String,
    @Expose val logo: String
) : Parcelable

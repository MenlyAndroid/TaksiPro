package ru.taksi.pro.android.mvvm.model.entity.authorization

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Profile(
    @SerializedName("birthdate")
    @Expose val birthDate: String,
    @SerializedName("firstname")
    @Expose val firstName: String,
    @Expose val id: Int,
    @SerializedName("lastname")
    @Expose val lastName: String,
    @Expose val license: License,
    @Expose val passport: Passport,
    @Expose val phone: String
)
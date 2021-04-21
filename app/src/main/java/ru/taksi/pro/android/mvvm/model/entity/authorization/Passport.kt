package ru.taksi.pro.android.mvvm.model.entity.authorization

import com.google.gson.annotations.Expose

data class Passport(
    @Expose val address: String,
    @Expose val date: String,
    @Expose val number: String,
    @Expose val series: String
)
package ru.taksi.pro.android.mvvm.model.entity.user

import com.google.gson.annotations.Expose

data class Passport(
    @Expose val address: String,
    @Expose val date: String,
    @Expose val number: String,
    @Expose val series: String
)
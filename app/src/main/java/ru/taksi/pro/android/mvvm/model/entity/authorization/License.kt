package ru.taksi.pro.android.mvvm.model.entity.authorization

import com.google.gson.annotations.Expose

data class License(
    @Expose val date: String,
    @Expose val expire: String,
    @Expose val number: String,
    @Expose val series: String
)
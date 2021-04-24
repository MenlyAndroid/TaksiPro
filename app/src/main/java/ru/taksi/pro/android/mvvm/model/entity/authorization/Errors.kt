package ru.taksi.pro.android.mvvm.model.entity.authorization

import com.google.gson.annotations.Expose

data class Errors(
    @Expose val tomanyrequests: String
)
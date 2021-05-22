package ru.taksi.pro.android.mvvm.model.entity.address

import com.google.gson.annotations.Expose

data class Suggestion(
    @Expose val value: String,
    @Expose val unrestricted_value: String
)
package ru.taksi.pro.android.mvvm.model.entity.authorization

import com.google.gson.annotations.Expose

data class ConfirmationCode(
    @Expose val deadline: Int,
    @Expose val requestTime: Int,
    @Expose val resubmitTime: Int,
    @Expose val success: Boolean
)
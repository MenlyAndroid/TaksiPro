package ru.taksi.pro.android.mvvm.model.entity.authorization

import com.google.gson.annotations.Expose

data class AuthorizationResponse(
        @Expose val success: Boolean,
        @Expose val user: User
)
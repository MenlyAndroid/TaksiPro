package ru.taksi.pro.android.mvvm.model.entity.authorization

import com.google.gson.annotations.Expose

data class RegistrationResponse(
    @Expose val createAt: String,
    @Expose val permissions: List<String>,
    @Expose val phone: String,
    @Expose val profile: Profile,
    @Expose val roles: List<String>,
    @Expose val status: String,
    @Expose val token: String
)
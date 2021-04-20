package ru.taksi.pro.android.mvvm.model.entity.user

import com.google.gson.annotations.Expose

data class UsersItem(
    @Expose val createAt: String,
    @Expose val permissions: List<String>,
    @Expose val phone: String,
    @Expose val profile: Profile,
    @Expose val roles: List<String>,
    @Expose val status: String,
    @Expose val token: String
)
package ru.taksi.pro.android.mvvm.model.entity.authorization

import com.google.gson.annotations.Expose
import ru.taksi.pro.android.mvvm.model.entity.cars.Car

data class AuthorizationResponse(
        @Expose val success: String,
        @Expose val user: User
)

data class User(
        @Expose val agregators: List<Any>,
        @Expose val car: Car,
        @Expose val createAt: String,
        @Expose val driverAccounts: List<Any>,
        @Expose val id: Int,
        @Expose val permissions: List<String>,
        @Expose val phone: String,
        @Expose val profile: Profile,
        @Expose val roles: List<String>,
        @Expose val status: Int,
        @Expose val tariff: Any,
        @Expose val token: String
)

package ru.taksi.pro.android.mvvm.model.entity.balance

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Balance : ArrayList<BalanceItem>()

data class BalanceItem(
    @SerializedName("агрегатор")
    @Expose val aggregator: String
)

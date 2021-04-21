package ru.taksi.pro.android.mvvm.model.entity.transaction

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Transaction : ArrayList<TransactionItem>()

data class TransactionItem(
    @SerializedName("агрегатор")
    @Expose val aggregator: String
)

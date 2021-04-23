package ru.taksi.pro.android.mvvm.model.network

import io.reactivex.rxjava3.core.Single

interface INetworkChecker {
    fun isConnect(): Single<Boolean>
}
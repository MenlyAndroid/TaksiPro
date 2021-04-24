package ru.taksi.pro.android.di.modules

import dagger.Module
import dagger.Provides
import ru.taksi.pro.android.app.TaxiProApplication
import ru.taksi.pro.android.domain.network.NetworkChecker
import ru.taksi.pro.android.mvvm.model.api.ApiService
import ru.taksi.pro.android.mvvm.model.network.INetworkChecker
import ru.taksi.pro.android.mvvm.model.repo.ITaxiProRepository
import ru.taksi.pro.android.mvvm.model.repo.retrofit.TaxiProRepository
import javax.inject.Singleton

@Module
class RepoModule {
    @Singleton
    @Provides
    fun networkStatus(app: TaxiProApplication): INetworkChecker = NetworkChecker(app)

    @Singleton
    @Provides
    fun getRepo(
        api: ApiService,
        networkChecker: INetworkChecker
    ): ITaxiProRepository = TaxiProRepository(api, networkChecker)
}
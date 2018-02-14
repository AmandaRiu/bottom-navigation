package com.riusoft.bottomnavigation.ui.order

import com.riusoft.bottomnavigation.data.AppDataManager
import com.riusoft.bottomnavigation.di.AppDataModule
import com.riusoft.bottomnavigation.di.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class OrderModule {
    @FragmentScope
    @Provides
    fun provideOrderPresenter(dataManager: AppDataManager): OrderContract.Presenter {
        return OrderPresenter(dataManager)
    }
}
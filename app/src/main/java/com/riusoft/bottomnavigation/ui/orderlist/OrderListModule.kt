package com.riusoft.bottomnavigation.ui.orderlist

import com.riusoft.bottomnavigation.data.AppDataManager
import com.riusoft.bottomnavigation.di.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class OrderListModule {
    @FragmentScope
    @Provides
    fun provideOrderListPresenter(dataManager: AppDataManager): OrderListContract.Presenter {
        return OrderListPresenter(dataManager)
    }

    @FragmentScope
    @Provides
    fun provideOrderListAdapter(presenter: OrderListContract.Presenter): OrderListAdapter {
        return OrderListAdapter(mutableListOf(), presenter)
    }
}

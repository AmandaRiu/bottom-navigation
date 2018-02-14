package com.riusoft.bottomnavigation.ui.orderlist

import com.riusoft.bottomnavigation.di.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class OrderListProvider {
    @FragmentScope
    @ContributesAndroidInjector(modules = arrayOf(OrderListModule::class))
    abstract fun orderListFragment(): OrderListFragment
}

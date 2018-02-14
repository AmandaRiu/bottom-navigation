package com.riusoft.bottomnavigation.di

import com.riusoft.bottomnavigation.ui.dashboard.DashboardModule
import com.riusoft.bottomnavigation.ui.main.MainActivity
import com.riusoft.bottomnavigation.ui.main.MainModule
import com.riusoft.bottomnavigation.ui.order.OrderProvider
import com.riusoft.bottomnavigation.ui.orderlist.OrderListProvider
import com.riusoft.bottomnavigation.ui.products.ProductsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(
            MainModule::class,
            DashboardModule::class,
            OrderProvider::class,
            OrderListProvider::class,
            ProductsModule::class))
    abstract fun provideMainActivityInjector(): MainActivity
}

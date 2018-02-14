package com.riusoft.bottomnavigation.ui.order

import com.riusoft.bottomnavigation.di.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class OrderProvider {
    @FragmentScope
    @ContributesAndroidInjector(modules = arrayOf(OrderModule::class))
    abstract fun orderFragment(): OrderFragment
}

package com.riusoft.bottomnavigation.ui.products

import com.riusoft.bottomnavigation.di.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ProductsModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun productsFragment(): ProductsFragment
}

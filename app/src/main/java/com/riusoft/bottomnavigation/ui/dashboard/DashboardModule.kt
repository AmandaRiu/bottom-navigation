package com.riusoft.bottomnavigation.ui.dashboard

import com.riusoft.bottomnavigation.di.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class DashboardModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun dashboardFragment(): DashboardFragment
}

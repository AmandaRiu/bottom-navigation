package com.riusoft.bottomnavigation.ui.main

import com.riusoft.bottomnavigation.di.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class MainModule {
    @ActivityScope
    @Provides
    fun provideMainPresenter(): MainContract.Presenter {
        return MainPresenter()
    }
}
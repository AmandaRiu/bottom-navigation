package com.riusoft.bottomnavigation.ui.main

import com.riusoft.bottomnavigation.data.AppDataManager
import com.riusoft.bottomnavigation.di.ActivityScope
import com.riusoft.bottomnavigation.di.AppDataModule
import dagger.Module
import dagger.Provides

@Module
class MainModule {
    @ActivityScope
    @Provides
    fun provideMainPresenter(dataManager: AppDataManager): MainContract.Presenter {
        return MainPresenter(dataManager)
    }
}
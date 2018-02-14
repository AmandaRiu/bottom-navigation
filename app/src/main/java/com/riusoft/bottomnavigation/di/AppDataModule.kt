package com.riusoft.bottomnavigation.di

import com.riusoft.bottomnavigation.data.AppDataManager
import dagger.Module
import dagger.Provides

@Module
class AppDataModule {
    @ApplicationScope
    @Provides
    fun appDataManager(): AppDataManager {
        return AppDataManager()
    }
}
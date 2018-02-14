package com.riusoft.bottomnavigation.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule {
    // Expose Application as injectable context
    @Binds
    internal abstract fun bindContext(application: Application): Context
}

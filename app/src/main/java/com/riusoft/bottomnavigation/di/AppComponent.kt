package com.riusoft.bottomnavigation.di

import android.app.Application
import com.riusoft.bottomnavigation.BottomNavigationApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@ApplicationScope
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ActivityBindingModule::class,
        AppDataModule::class
))
interface AppComponent : AndroidInjector<BottomNavigationApp> {
    override fun inject(app: BottomNavigationApp)

    // Allows us to inject the application without having to instantiate any modules,
    // and provides the Application in the app graph.
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
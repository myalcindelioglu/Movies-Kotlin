package com.myd.movies.common.dagger.component

import android.app.Application
import com.myd.movies.App
import com.myd.movies.common.dagger.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by MYD on 5/31/18.
 */
@Singleton
@Component(modules = [(DataSourceModule::class),
    (LocalDbModule::class),
    (PresenterModule::class),
    (AppModule::class),
    (ActivityBindingModule::class),
    (AndroidSupportInjectionModule::class)])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}
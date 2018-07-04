package com.myd.movies.common.dagger.module

import com.myd.movies.common.dagger.annotations.ActivityScoped
import com.myd.movies.mvp.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by MYD on 5/31/18.
 */
@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class, MainActivityProvidesModule::class))
    internal abstract fun mainActivity(): MainActivity
}
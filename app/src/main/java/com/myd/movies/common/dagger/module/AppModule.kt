package com.myd.movies.common.dagger.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

/**
 * Created by MYD on 5/31/18.
 */
@Module
abstract class AppModule {
    @Binds
    internal abstract fun bindContext(application: Application): Context
}
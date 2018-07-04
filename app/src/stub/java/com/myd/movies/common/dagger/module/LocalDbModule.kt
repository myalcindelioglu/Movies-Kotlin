package com.myd.movies.common.dagger.module

import android.arch.persistence.room.Room
import android.content.Context
import com.myd.movies.common.data.db.MovieDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by MYD on 6/3/18.
 */
@Module
class LocalDbModule {

    @Singleton
    @Provides
    fun provideMovieDB(context: Context) : MovieDB {
        return Room.databaseBuilder(context.applicationContext,
                MovieDB::class.java, "MoviesTest.db")
                .build()
    }
}
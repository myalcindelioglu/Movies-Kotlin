package com.myd.movies.common.dagger.module

import android.arch.lifecycle.MutableLiveData
import com.myd.movies.common.data.db.MovieDB
import com.myd.movies.common.data.local.LocalCache
import com.myd.movies.common.data.local.LocalMovieCache
import com.myd.movies.common.data.remote.MoviesDataSource
import com.myd.movies.common.data.repository.MoviesRepository
import com.myd.movies.common.data.repository.Repository
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executors
import javax.inject.Singleton

/**
 * Created by MYD on 6/4/18.
 */
@Module
class PresenterModule {

    @Singleton
    @Provides
    fun provideMovieLiveData(): MutableLiveData<String> {
        return MutableLiveData()
    }

    @Singleton
    @Provides
    fun provideLocalCache(movieDB: MovieDB): LocalCache {
        return LocalMovieCache(movieDB.getMovieDao(), Executors.newSingleThreadExecutor())
    }
    @Singleton
    @Provides
    internal fun provideMoviesRepository(localCache: LocalCache, moviesDataSource: MoviesDataSource) : Repository {
        return MoviesRepository(localCache, moviesDataSource)
    }
}